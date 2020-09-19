package com.shkj.task;

import com.shkj.bean.BusinessTripLeave;
import com.shkj.bean.ChargeUser;
import com.shkj.bean.Holiday;
import com.shkj.bean.ResponseBean;
import com.shkj.service.BusinessTripLeaveService;
import com.shkj.service.ChargeUserService;
import com.shkj.service.DailyReportInfoService;
import com.shkj.service.HolidayService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 定时任务：每天执行未发送日报情况通报
 */
@Component
public class JobTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public DailyReportInfoService infoService;
    @Autowired
    public HolidayService holidayService;
    @Autowired
    public ChargeUserService chargeUserService;
    @Autowired
    public BusinessTripLeaveService businessTripLeaveService;
    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0 30 16 ? * *")
    @SchedulerLock(name = "demoJobName", lockAtMostFor = "5m", lockAtLeastFor = "5m")
    public void pushInfo() {
        logger.info("=============开始执行未发送日报通报定时任务===============");
        // 获取当前日期所在年份、月份
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH) + 1;

        // 获取当月的正常工作日（不考虑节假日）
        List<String> dates = GetWorkDayUtil.getDates(year, month);
        // 获取上个月的正常工作日（不考虑节假日）
        List<String> lastdates = null;
        if(month == 1) {
            date.add(Calendar.YEAR,-1);
            lastdates = GetWorkDayUtil.getDates(date.get(Calendar.YEAR), 12); // 去年12月
        } else {
            lastdates = GetWorkDayUtil.getDates(year, month-1); // 上个月
        }
        //合并两个集合
        dates.addAll(lastdates);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String today = sf.format(new Date()); // 今天日期
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMM"); // 今天归属年月
        // 查询节假日表中当月和上个月的假日和补班日期情况
        List<Holiday> listHoliday = holidayService.queryByMonth(sf2.format(new Date()));
        // 获取最全的工作日集合
        for(Holiday holiday: listHoliday){
            if(holiday.getType() == "0") {
                if(dates.indexOf(holiday.getDay()) > 0) { //如果类型是“假日”，且工作日集合中存在，则从集合中移除
                    dates.remove(dates.indexOf(holiday.getDay()));
                }
            } else {
                if(dates.indexOf(holiday.getDay()) < 0) { //如果类型是“补班”，且工作日集合中不存在，则添加到集合中
                    dates.add(holiday.getDay());
                }
            }
        }
        // 对工作日集合进行排序
        Collections.sort(dates);

        // 判断当日是否是工作日，如果不是则跳出，否则向前找最近的一个工作日作为日报统计日
        if(dates.indexOf(today) > 0) {
            // 找出今天所在集合中的下标
            int inx = dates.indexOf(today);
            String dailyDay = dates.get(inx-1); //日报统计日期
            logger.info("【日报统计日期】：" + dailyDay);

            String noticeMsg = "关于" + dailyDay + "上报日志的通报：\n\n"; //通报消息
            String nosendMsg = "未报送：";
            String noDaySendMsg = "当日未报送:";
            String businessMsg = "出差人员：";
            String holidayMsg = "休假人员：";
            // todo 根据日报统计日期查询日报记录表中未发送日报的人员姓名(条件：>=日报统计日期之后 <=当前时间之前）
            List<ChargeUser> nosendUsers = chargeUserService.queryNosendDaily(dailyDay); // 未上报日志人员（排除休假、出差人员）
            List<ChargeUser> nosendForDay = chargeUserService.queryNosendForDay(dailyDay); // 未当日报送人员（排除休假、出差人员）
            List<BusinessTripLeave> noneedSend = businessTripLeaveService.queryByDay(dailyDay); // 当日出差或休假人员
            noticeMsg += "未报送：";
            for(ChargeUser user: nosendUsers){
                nosendMsg += user.getUserName() + "、";
            }
            if(nosendMsg.contains("、")) {
                nosendMsg = nosendMsg.substring(1, nosendMsg.length() - 1)  + "\n\n";
            } else {
                nosendMsg += "\n\n";
            }

            for(ChargeUser user: nosendForDay) {
                noDaySendMsg += user.getUserName() + "、";
            }

            if(noDaySendMsg.contains("、")) {
                noDaySendMsg = noDaySendMsg.substring(1, noDaySendMsg.length() - 1)  + "\n\n";
            } else {
                noDaySendMsg += "\n\n";
            }

            for(BusinessTripLeave user: noneedSend) {
                if(user.getType().equals("0")) {
                    businessMsg += user.getUserName() + "、";
                } else {
                    holidayMsg += user.getUserName() + "、";
                }
            }
            if(businessMsg.contains("、")) {
                businessMsg = businessMsg.substring(1, businessMsg.length() - 1)  + "\n\n";
            } else {
                businessMsg += "\n\n";
            }
            if(holidayMsg.contains("、")) {
                holidayMsg = holidayMsg.substring(1, holidayMsg.length() - 1)  + "\n\n";
            } else {
                holidayMsg += "\n\n";
            }

            // 合并消息内容
            noticeMsg = noticeMsg + nosendMsg + noDaySendMsg + businessMsg + holidayMsg
                    + "经营团队、管理部门总经理室成员每工作日应在招呼群中报送日志，公司部次日将会对未报送及未及时报送日志的负责人进行通报，如有疑问请在招呼群众告知 陈博（一事通 297807），谢谢您的配合";
            logger.info("【通报内容】：" + noticeMsg);
            String apiUrl = "http://msgps.bcs.cmbchina.cn/msgps/zhrobot/sendGroupMsg"; //测试环境地址
//          String apiUrl = "http://msgpsdmz.bcs.cmbchina.cn/msgps/zhrobot/sendGroupMsg"; //Dmz生产地址
//          String apiUrl = "http://msgpsbiz.bcs.cmbchina.cn/msgps/zhrobot/sendGroupMsg"; //BIZ生产地址
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> requestParam = new HashMap<>();
            requestParam.put("msgCode", "0001"); // 招呼机器人code，需要提供
            requestParam.put("msgContent", noticeMsg); //招呼内容
            requestParam.put("groupId", "900000395206"); // 群聊ID，需要提供 （正式环境需要从报送日志表中获取）
            logger.info("【通报内容】：" + requestParam.toString());
            HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(requestParam, headers);

            ResponseEntity<ResponseBean> entity = restTemplate.postForEntity(apiUrl, request, ResponseBean.class);
            ResponseBean resp = entity.getBody();
            logger.info("【收到招呼响应结果】：" + resp.toString());

        } else {
            logger.info("==============今天非工作日，无需进行通报，任务执行结束==============");
        }
    }
}
