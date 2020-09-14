package com.shkj.task;

import com.shkj.service.DailyReportInfoService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 定时任务：每天执行未发送日报情况通报
 */
@Component
public class JobTask {
    @Autowired
    public DailyReportInfoService infoService;
    @Scheduled(cron = "0 0 6 ? * *")
    @SchedulerLock(name = "demoJobName", lockAtMostFor = "5m", lockAtLeastFor = "5m")
    public void pushInfo() {
        // ToDo 关联查询当日日报数据和负责人名单，匹配未发送日报的人员
        /**
         * 1、判断当前日期是否是节假日：是-跳过；否-进入下一步
         * 2、
         *
         *
         *
         *
         *
         */
    }
}
