package com.wsy.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wsy.model.Job;
import com.wsy.model.Task;
import com.wsy.model.TaskUser;
import com.wsy.model.biz.Result;
import com.wsy.schedule.ScheduleManager;
import com.wsy.util.Constant;
import com.wsy.util.LogUtil;
import com.wsy.util.ResultFactory;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 任务服务类
 * Created by Lenovo on 2017/11/4.
 */
public class TaskService {

    /**
     * 根据状态查询任务
     *
     * @param status 状态
     * @return 任务列表
     */
    public List<Task> queryTaskByStatus(int status) {
        return Task.dao.find(Db.getSqlPara("index.findTaskByStatus", status));
    }

    /**
     * 根据编码查询job
     *
     * @param code taskId_date
     * @return job列表
     */
    public List<Job> queryJobByCode(String code) {
        return Job.dao.find(Db.getSqlPara("index.findJobByCode", code));
    }

    /**
     * 根据任务id查询执行者
     *
     * @param taskId 任务id
     * @return result
     */
    public List<Record> queryExecutorsById(int taskId) {
        return Db.find("select t.*, t1.nick_name from task_user t left join user t1 on t.user_id = t1.id where t.task_id = ? ", taskId);
    }

    /**
     * 查询
     *
     * @return result
     */
    public Result queryFamilyTask(int familyid, int page, int size) {
        return ResultFactory.success(Task.dao.paginate(page, size, "select *", "from task where id in (select task_id from task_user where user_id in (select id from user where family_id = ?))", familyid));
    }

    /**
     * 校验cron表达式是否合法
     *
     * @param cronStr 表达式
     * @return Result
     */
    public Result validateCron(String cronStr) {
        try {
            return ResultFactory.success(new CronExpression(cronStr).getNextValidTimeAfter(new Date()));
        } catch (ParseException e) {
            return ResultFactory.error(null);
        }
    }

    /**
     * 保存task
     * @param task 任务
     * @param executorList 执行人员id列表
     * @param userId 当前用户
     * @return Result
     */
    @Before(Tx.class)
    public Result save(Task task, List<Integer> executorList, int userId) {
        // 判断是否新增
        if (task.getId() == 0) {
            task.setCreatedBy(userId).setCreatedTime(new Date());

            // 状态
            if (task.getStartTime().getTime() > new Date().getTime()) {
                task.setStatus(Constant.TASK_STATUS.NOT_START);
            } else {
                if (null != task.getEndTime() && task.getEndTime().getTime() < new Date().getTime()) {
                    task.setStatus(Constant.TASK_STATUS.END);
                } else {
                    task.setStatus(Constant.TASK_STATUS.RUNNING);
                }
            }

            // 启动调度任务
            try {
                Date date = ScheduleManager.startTask(task);
                task.setNextFireTime(date).save();

                List<TaskUser> taskUserList = new ArrayList<>();
                for (Integer user : executorList) {
                    taskUserList.add(new TaskUser().setTaskId(task.getId()).setUserId(user));
                }
                Db.batchSave(taskUserList, 100);

                LogUtil.LogType.taskLog.info("start task {} success, next fire time is:", task.getName(), date);
                return ResultFactory.success(date);
            } catch (SchedulerException e) {
                e.printStackTrace();
                LogUtil.LogType.errorLog.error("start task {} error: ", task.getName(), e.getMessage());
            }

        }
        return ResultFactory.error(null);
    }
}
