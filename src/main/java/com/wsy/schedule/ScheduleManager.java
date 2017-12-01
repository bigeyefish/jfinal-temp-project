package com.wsy.schedule;

import com.wsy.model.Task;
import com.wsy.service.TaskService;
import com.wsy.util.Constant;
import com.wsy.util.LogUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.List;

/**
 * 任务调度管理类
 * Created by Lenovo on 2017/11/4.
 */
public class ScheduleManager {

    private static TaskService taskService;

    private ScheduleManager() {
        taskService = new TaskService();
    }

    private static Scheduler scheduler = null;

    /**
     * 初始化调度
     * @return 是否启动成功
     */
    public static boolean init() {
        try {

            // 创建一个schedule
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();

            List<Task> taskList = taskService.queryTaskByStatus(true);
            for (Task task : taskList) {
                Date date = startTask(task);
                task.setNextFireTime(date).update();
            }
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            LogUtil.LogType.errorLog.error("start task error: ", e.getMessage());
            return false;
        }
    }

    /**
     * 启动task
     * @param task 任务
     * @return 下次执行时间
     * @throws SchedulerException exception
     */
    public static Date startTask(Task task) throws SchedulerException {
        // 创建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(task.getId().toString()).build();
        jobDetail.getJobDataMap().put(Constant.KEY_TASK_INFO, task);

        // 创建trigger
        TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(task.getId().toString()).startAt(task.getStartTime()).
                withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression()));
        if (task.getEndTime() != null) {
            triggerBuilder.endAt(task.getEndTime());
        }
        CronTrigger trigger = triggerBuilder.build();
        scheduler.scheduleJob(jobDetail, trigger);
        return trigger.getNextFireTime();
    }

    /**
     * 删除task
     * @param taskId taskId
     * @return 是否成功
     * @throws SchedulerException exception
     */
    public static boolean deleteTask(int taskId) throws SchedulerException {
        return scheduler.deleteJob(JobKey.jobKey(String.valueOf(taskId)));
    }
}
