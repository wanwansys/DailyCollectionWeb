package com.shkj.task;

import com.shkj.bean.Holiday;
import com.shkj.service.DailyReportInfoService;
import com.shkj.service.HolidayService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 定时任务：每天执行未发送日报情况通报
 */
@Component
public class JobTask {
    @Autowired
    public DailyReportInfoService infoService;
    @Autowired
    public HolidayService holidayService;

    @Scheduled(cron = "0 0 6 ? * *")
    @SchedulerLock(name = "demoJobName", lockAtMostFor = "5m", lockAtLeastFor = "5m")
    public void pushInfo() {
        // ToDo 关联查询当日日报数据和负责人名单，匹配未发送日报的人员
        /**
         * 取到本次需要统计的日报日期规则如下：
         *
         * 1、判断当天是否是节假日：是-跳过；否-进入2
         * 2、当日不是节假日则判断昨天是否是节假日：是-判断前天是否是节假日，一直往前推，直到取到不是节假日那天；否-判断当日是否是周末，进入3
         * 3、如果是周末，则判断是否是“补班”，进入4；若不是则往前推一天判断是否是周末，如果是，则再往前推一天，否则进入4
         * 4、如果是“补班”，则成功取到“日报统计日期”，
         *
         */
        // 获取当前日期所在年份、月份
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH) + 1;

        // 获取当月的正常工作日（不考虑节假日）
        List<String> dates = GetWorkDayUtil.getDates(year, month);

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMM");
        // 查询节假日表中当月的假日和补班日期情况
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

        //
    }
}
