package com.shkj.task;

import com.cmbchina.channel.util.StringUtils;
import com.shkj.bean.BusinessTripLeave;
import com.shkj.bean.ChargeUser;
import com.shkj.bean.Holiday;
import com.shkj.bean.ResponseBean;
import com.shkj.mapper.SysParamMapper;
import com.shkj.service.*;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;


/**
 * 定时任务：每天执行未发送日报情况通报
 */
@Component
public class JobTask implements SchedulingConfigurer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    @SuppressWarnings("all")
    SysParamService sysParamService;
    
    @Autowired
    public DailyReportInfoService infoService;
    @Autowired
    public HolidayService holidayService;
    @Autowired
    public ChargeUserService chargeUserService;
    @Autowired
    public BusinessTripLeaveService businessTripLeaveService;
    @Autowired
    public RestTemplate restTemplate;

    @Override
    @SchedulerLock(name = "执行通报任务锁", lockAtMostFor = "2m", lockAtLeastFor = "2m")
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
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
                        
                     // 休假或出差
                        for(BusinessTripLeave user: noneedSend) {
                            if(user.getType().equals("0")) {
                                businessMsg += user.getUserName() + "、";
                            } else {
                                holidayMsg += user.getUserName() + "、";
                            }
                        }
                        // 出差
                        if(businessMsg.contains("、")) {
                            businessMsg = businessMsg.substring(0, businessMsg.lastIndexOf("、"))  + "\n\n";
                        } else {
                            businessMsg += "\n\n";
                        }
                        // 休假
                        if(holidayMsg.contains("、")) {
                            holidayMsg = holidayMsg.substring(0, holidayMsg.lastIndexOf("、"))  + "\n\n";
                        } else {
                            holidayMsg += "\n\n";
                        }
                        
                        // 未报送
                        for(ChargeUser user: nosendUsers){
                        	// 如果休假或出差，则跳过
                        	if(businessMsg.contains(user.getUserName()) || holidayMsg.contains(user.getUserName())) {
                        		continue;
                        	} else {
                        		nosendMsg += user.getUserName() + "、";
                        	}
                        }

                        if(nosendMsg.contains("、")) {
                            nosendMsg = nosendMsg.substring(0, nosendMsg.lastIndexOf("、"))  + "\n\n";
                        } else {
                            nosendMsg += "\n\n";
                        }
                        // 当日未报送
                        for(ChargeUser user: nosendForDay) {
                        	// 如果休假或出差，则跳过
                        	if(businessMsg.contains(user.getUserName()) || holidayMsg.contains(user.getUserName())) {
                        		continue;
                        	} else {
                        		// 如果“未报送”中有此人，则跳过
                            	if(nosendMsg.contains(user.getUserName())) {
                            		continue;
                            	} else {
                                    noDaySendMsg += user.getUserName() + "、";
                            	}
                        	}
                        }
                        
                        if(noDaySendMsg.contains("、")) {
                            noDaySendMsg = noDaySendMsg.substring(0, noDaySendMsg.lastIndexOf("、"))  + "\n\n";
                        } else {
                            noDaySendMsg += "\n\n";
                        }
                        
                        // 合并消息内容
                        noticeMsg = noticeMsg + nosendMsg + noDaySendMsg + businessMsg + holidayMsg
                                + "经营团队、管理部门总经理室成员每工作日应在招呼群中报送日志，公司部次日将会对未报送及未及时报送日志的负责人进行通报，如有疑问请在招呼群众告知 陈博（一事通 297807），谢谢您的配合";
                        logger.info("【通报内容】：" + noticeMsg);
                        
                        String apiUrl = sysParamService.getValueBykey("apiUrl");
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        Map<String, Object> requestParam = new HashMap<>();
                        requestParam.put("msgCode", sysParamService.getValueBykey("msgCode")); // 招呼机器人code，需要提供
                        requestParam.put("msgContent", noticeMsg); //招呼内容
                        requestParam.put("groupId", sysParamService.getValueBykey("groupId")); // 群聊ID，需要提供 （正式环境需要从报送日志表中获取）
                        logger.info("【发送通报内容】：" + requestParam.toString());
                        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(requestParam, headers);

                        ResponseEntity<ResponseBean> entity = restTemplate.postForEntity(apiUrl, request, ResponseBean.class);
                        ResponseBean resp = entity.getBody();
                        logger.info("【收到招呼响应结果】：" + resp.toString());

                    } else {
                        logger.info("==============今天非工作日，无需进行通报，任务执行结束==============");
                    }
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = sysParamService.getValueBykey("cron");
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                        logger.error("未取到任务表达式，请后台配置。。。");
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
