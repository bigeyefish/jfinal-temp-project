package com.wsy.schedule;

import com.jfinal.ext.kit.DateKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.wsy.model.Task;
import com.wsy.service.TaskService;
import com.wsy.util.Constant;
import com.wsy.util.LogUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 生成job的job
 * Created by Lenovo on 2017/11/4.
 */
public class MyJob implements Job {

    private TaskService taskService;

    public MyJob() {
        taskService = new TaskService();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        int taskId = (int)jobExecutionContext.getJobDetail().getJobDataMap().get(Constant.KEY_TASK_ID);

        String jobCode = taskId + "_" + DateKit.toStr(new Date());

        // 检查是否已经生成job
        List<com.wsy.model.Job> jobList = taskService.queryJobByCode(jobCode);
        if (jobList.size() > 0) {
            LogUtil.LogType.taskLog.info("job code {} has created", jobCode);
            return;
        }

        Task task = taskService.queryById(taskId);

        // 生成job
        List<com.wsy.model.Job> newJobList = new ArrayList<>();

        // 执行人
        List<Record> executorList = taskService.queryExecutorsById(task.getId());

        if (task.getType() == Constant.TaskType.BYTURN) {
            // 轮流类型要先找到上一次执行的人
            com.wsy.model.Job job = taskService.queryLastFinishedJob(taskId);
            int index = 0;
            if (null != job) {
                for (int i = 0; i < executorList.size(); i++) {
                    if (executorList.get(i).get("user_id") == job.getUserId()) {
                        index = i;
                        break;
                    }
                }
            }
            newJobList.add(MyJob.jobGenerator(task, jobCode, executorList.get(index % executorList.size()).getInt("user_id")));
        } else {
            newJobList.addAll(executorList.stream().map(record -> MyJob.jobGenerator(task, jobCode, record.getInt("user_id"))).collect(Collectors.toList()));
        }

        Db.batchSave(newJobList, 100);
        task.setNextFireTime(jobExecutionContext.getNextFireTime()).update();
        LogUtil.LogType.taskLog.info("job {} fired !!!, next fire time is {}", task.getName(), jobExecutionContext.getNextFireTime());
    }

    /**
     * 根据task生成job
     * @param task 任务实体
     * @return result
     */
    private static com.wsy.model.Job jobGenerator(Task task, String code, int userId) {
        com.wsy.model.Job job = new com.wsy.model.Job();
        job.setCode(code);
        job.setTaskId(task.getId());
        job.setUserId(userId);
        job.setCreatedTime(new Date());
        job.setStatus(task.getType() == Constant.TaskType.COMPETE ? Constant.JOB_STATUS.TO_BE_GRAB : Constant.JOB_STATUS.RUNNING);
        job.setPlanAmount(task.getAmount());
        return job;
    }
}
