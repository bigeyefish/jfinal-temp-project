package com.wsy.service;

import com.jfinal.plugin.activerecord.Db;
import com.wsy.model.Job;
import com.wsy.model.Task;
import com.wsy.model.biz.Result;
import com.wsy.util.Constant;
import com.wsy.util.ResultFactory;

import java.util.List;

/**
 * 任务服务类
 * Created by Lenovo on 2017/11/4.
 */
public class TaskService {

    /**
     * 根据状态查询任务
     * @param status 状态
     * @return List<Task>
     */
    public List<Task> queryTaskByStatus(int status) {
        return Task.dao.find(Db.getSqlPara("index.findTaskByStatus", status));
    }

    /**
     * 根据编码查询job
     * @param code taskId_date
     * @return List<Job>
     */
    public List<Job> queryJobByCode(String code) {
        return Job.dao.find(Db.getSqlPara("index.findJobByCode", code));
    }

    /**
     * 查询
     * @return result
     */
    public Result queryFamilyTask(int userId, int page, int size) {
        return ResultFactory.success(Task.dao.paginate(page, size, "select *", "from task where (executor = ? and type = " +
                Constant.TaskType.PERSONAL + ") or (type != " + Constant.TaskType.PERSONAL +
                " and executor = (select family_id from user where id = ?))", userId, userId));
    }
}
