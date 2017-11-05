package com.wsy.service;

import com.jfinal.plugin.activerecord.Db;
import com.wsy.model.Job;
import com.wsy.model.Task;

import java.util.List;

/**
 * Created by Lenovo on 2017/11/4.
 */
public class TaskService {

    /**
     * 根据状态查询任务
     * @param status
     * @return
     */
    public List<Task> queryTaskByStatus(int status) {
        return Task.dao.find(Db.getSqlPara("index.findTaskByStatus", status));
    }

    /**
     * 根据编码查询job
     * @param code taskId_date
     * @return
     */
    public List<Job> queryJobByCode(String code) {
        return Job.dao.find(Db.getSqlPara("index.findJobByCode", code));
    }
}
