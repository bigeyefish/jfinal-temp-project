package com.wsy.schedule;

import com.jfinal.kit.StrKit;
import com.wsy.model.Task;
import com.wsy.service.TaskService;
import com.wsy.util.Constant;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.List;

/**
 * 任务调度管理类
 * Created by Lenovo on 2017/11/4.
 */
public class ScheduleManager {

    private static ScheduleManager ourInstance = new ScheduleManager();

    private static TaskService taskService;

    public static ScheduleManager getInstance() {
        return ourInstance;
    }

    private ScheduleManager() {
        taskService = new TaskService();
    }

    public static boolean init() {
        Scheduler scheduler = null;
        try {

            // 创建一个schedule
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();

            List<Task> taskList = taskService.queryTaskByStatus(Constant.TASK_ACTIVE);
            for (Task task : taskList) {
                if (StrKit.notBlank(task.getCronExpression())) {

                    // 创建JobDetail
                    JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(task.getId().toString()).build();
                    jobDetail.getJobDataMap().put(Constant.KEY_TASK_INFO, task);

                    // 创建trigger
                    TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(task.getId().toString()).startAt(task.getNextFireTime()).
                            withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression()));
                    if (task.getEndTime() != null) {
                        triggerBuilder.endAt(task.getEndTime());
                    }

                    scheduler.scheduleJob(jobDetail, triggerBuilder.build());
                }
            }

            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
