package com.wsy.schedule;

import com.wsy.service.TaskService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 检查并设置过期的job
 * Created by sanyihwang on 2017/12/6.
 */
public class ExpireJob implements Job {

    private TaskService taskService;

    public ExpireJob() {
        taskService = new TaskService();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        taskService.setJobsExpire();
    }
}
