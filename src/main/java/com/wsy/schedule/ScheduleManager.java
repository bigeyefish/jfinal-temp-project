package com.wsy.schedule;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
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

    private static TaskService taskService = new TaskService();
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

            // 启动业务调度任务
            List<Task> taskList = taskService.queryTaskByStatus(true);
            for (Task task : taskList) {
                Date date = startTask(task);
                task.setNextFireTime(date).update();
            }

            // 启动系统调度任务
            Prop prop = PropKit.use("cron.properties");
            for (Object key : prop.getProperties().keySet()) {
                String cron_job = prop.get(key.toString());
                String[] arr = cron_job.split(Constant.PROP_SEP_SIGN);
                if (arr.length < 2) {
                    LogUtil.LogType.errorLog.error("the format of cron config [{}] is wrong !", cron_job);
                    return false;
                }

                @SuppressWarnings("unchecked")
                JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(arr[1])).withIdentity(key.toString()).build();

                TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(key.toString()).
                        withSchedule(CronScheduleBuilder.cronSchedule(arr[0]));
                CronTrigger trigger = triggerBuilder.build();
                scheduler.scheduleJob(jobDetail, trigger);
            }

            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            LogUtil.LogType.errorLog.error("start task error: {}", e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            LogUtil.LogType.errorLog.error("can not find class: {}", e.getMessage());
            e.printStackTrace();
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
        jobDetail.getJobDataMap().put(Constant.KEY_TASK_ID, task.getId());

        // 创建trigger
        TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(task.getId().toString()).
                startAt(null == task.getNextFireTime() ? task.getStartTime() : task.getNextFireTime()).
                withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression()));
        if (task.getEndTime() != null) {
            triggerBuilder.endAt(task.getEndTime());
        }
        CronTrigger trigger = triggerBuilder.build();
        scheduler.scheduleJob(jobDetail, trigger);
        return trigger.getNextFireTime();
    }

    /**
     * 更新task
     * @param task task
     */
    public static Date updateTask(Task task) throws SchedulerException {

        TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(task.getId().toString()).
                startAt(null == task.getNextFireTime() ? task.getStartTime() : task.getNextFireTime()).
                withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression()));
        if (task.getEndTime() != null) {
            triggerBuilder.endAt(task.getEndTime());
        }
        CronTrigger trigger = triggerBuilder.build();
        scheduler.rescheduleJob(TriggerKey.triggerKey(task.getId().toString()), trigger);
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
