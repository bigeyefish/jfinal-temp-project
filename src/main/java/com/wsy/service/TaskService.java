package com.wsy.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.json.JFinalJson;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wsy.model.Job;
import com.wsy.model.Task;
import com.wsy.model.TaskUser;
import com.wsy.model.biz.Result;
import com.wsy.schedule.ScheduleManager;
import com.wsy.util.Constant;
import com.wsy.util.DateUtil;
import com.wsy.util.LogUtil;
import com.wsy.util.ResultFactory;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 任务服务类
 * Created by Lenovo on 2017/11/4.
 */
public class TaskService {

    private static String EDITABLE_FIELD[] = {"name", "desc", "type", "score", "priority", "alarm_type", "amount",
            "unit", "measure_type", "expire_type", "end_time", "cron_expression", "start_time"};

    private UserService userService = new UserService();

    /**
     * 根据状态查询任务
     *
     * @param isActive 是否有效状态
     * @return 任务列表
     */
    public List<Task> queryTaskByStatus(boolean isActive) {
        return Task.dao.find(Db.getSqlPara("index.findTaskByStatus", isActive));
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
        return Db.find("select t.*, t1.nick_name from task_user t left join user t1 on t.user_id = t1.id where t.task_id = ? order by t.user_id", taskId);
    }

    /**
     * 查询
     *
     * @return result
     */
    public Result queryFamilyTask(int familyid, int page, int size) {
        return ResultFactory.success(Task.dao.paginate(page, size, "select *",
                "from task where is_active = 1 and id in (select task_id from task_user where user_id in " +
                        "(select id from user where family_id = ?))", familyid));
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
            return ResultFactory.createResult(Constant.ResultCode.ILLEGAL_CRON);
        }
    }

    /**
     * 根据id查数据
     *
     * @param taskId taskId
     * @return Task
     */
    public Task queryById(int taskId) {
        return Task.dao.findById(taskId);
    }

    /**
     * 保存task
     *
     * @param task         任务
     * @param executorList 执行人员id列表
     * @param userId       当前用户
     * @return Result
     */
    @Before(Tx.class)
    public Result save(Task task, List<Integer> executorList, int userId) {

        Result result = validateCron(task.getCronExpression());
        if (result.getCode() != Constant.ResultCode.SUCCESS) {
            return result;
        }

        // 判断是否新增
        if (task.getId() == null) {
            task.setCreatedBy(userId).setCreatedTime(new Date());

            // 启动调度任务
            try {
                task.setNextFireTime((Date) result.getData()).save();
                Date date = ScheduleManager.startTask(task);

                List<TaskUser> taskUserList = new ArrayList<>();
                for (Integer user : executorList) {
                    taskUserList.add(new TaskUser().setTaskId(task.getId()).setUserId(user));
                }
                Db.batchSave(taskUserList, 100);

                LogUtil.LogType.taskLog.info("start task {} success, next fire time is: {}", task.getName(), date);
                return ResultFactory.success(date);
            } catch (SchedulerException e) {
                e.printStackTrace();
                LogUtil.LogType.errorLog.error("start task {} error: ", task.getName(), e.getMessage());
            }

        } else {
            try {
                // 先把原来的查出来
                Task oldTask = queryById(task.getId());

                // 需要替换schedule中task字段 end_time, cron_expression, startTime,
                if (!task.getCronExpression().equals(oldTask.getCronExpression()) || task.getStartTime().getTime() != oldTask.getStartTime().getTime() ||
                        !DateUtil.isEqual(task.getEndTime(), oldTask.getEndTime())) {
                    oldTask.setNextFireTime(ScheduleManager.updateTask(task));
                }

                // 可编辑直接保存字段 name, desc, type, score, priority, alarm_type, amount, unit, measure_type, expire_type
                for (String field : EDITABLE_FIELD) {
                    oldTask.set(field, task.get(field));
                }

                // 保存task
                oldTask.update();
                Db.update("delete from task_user where task_id = ?", task.getId());
                List<TaskUser> taskUserList = new ArrayList<>();
                for (Integer user : executorList) {
                    taskUserList.add(new TaskUser().setTaskId(task.getId()).setUserId(user));
                }
                Db.batchSave(taskUserList, 100);
                LogUtil.LogType.taskLog.info("update task {} success, next fire time is: {}", task.getName(), oldTask.getNextFireTime());
                return ResultFactory.success(oldTask.getNextFireTime());
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.LogType.errorLog.error("update task {} error: ", task.getName(), e.getMessage());
            }
        }
        return ResultFactory.error(null);
    }

    /**
     * 删除task (不进行物理删除)
     *
     * @param taskId taskId
     * @return Result
     */
    @Before(Tx.class)
    public Result deleteTask(int taskId) {
        try {

            // 删除quartz中job
            if (!ScheduleManager.deleteTask(taskId)) {
                return ResultFactory.createResult(Constant.ResultCode.DELETE_QUARTZ_JOB_ERROR);
            }

            // 逻辑删除数据
            queryById(taskId).setIsActive(false).update();
            Db.update("update job set status = ? where task_id = ? ", Constant.JOB_STATUS.OFF_LINE, taskId);
            return ResultFactory.success(null);
        } catch (SchedulerException e) {
            e.printStackTrace();
            LogUtil.LogType.errorLog.error("delete task [{}] error: {}", taskId, e.getMessage());
        }
        return ResultFactory.error(null);
    }

    /**
     * 根据taskId查询最后一次执行完成的job记录
     *
     * @param taskId taskId
     * @return Job
     */
    public Job queryLastFinishedJob(int taskId) {
        return Job.dao.findFirst("select * from job where task_id = ? and status = ?", taskId, Constant.JOB_STATUS.FINISHED);
    }

    /**
     * 查询人员对应的job列表
     *
     * @param userId     用户id
     * @param page       页码
     * @param size       数量
     * @param queryParam 查询参数
     * @return job列表
     */
    public Result queryJobByUserId(int userId, int page, int size, Map<String, String[]> queryParam) {
        StringBuilder sql = new StringBuilder("from job t left join task t1 on t.task_id = t1.id where t.user_id = ?");
        List<Object> param = new ArrayList<>();
        param.add(userId);
        if (queryParam.get("name") != null) {
            sql.append(" and t1.name like concat('%', ?, '%')");
            param.add(queryParam.get("name")[0]);
        }
        if (queryParam.get("status") != null) {
            sql.append(" and t.status = ?");
            param.add(queryParam.get("status")[0]);
        }
        sql.append(" order by t.created_time desc");
        Page<Record> result = Db.paginate(page, size, "select t.*, t1.name, t1.type, t1.priority, t1.unit, t1.expire_type, t1.score, t1.measure_type",
                sql.toString(), param.toArray());
        for (Record record : result.getList()) {
            record.set("expireDate", DateUtil.getTaskExpireDate(record.getDate("created_time"), record.getInt("expire_type")));
        }
        return ResultFactory.success(JSONObject.parse(JFinalJson.getJson().toJson(result)));
    }

    /**
     * job完成提交
     *
     * @param param
     * @return
     */
    @Before(Tx.class)
    public Result finishJob(Job param, int userId) {
        if (null == param.getId()) {
            return ResultFactory.error(Constant.ResultCode.LEAK_PARAM);
        }
        List<Job> jobList = new ArrayList<>();
        Job resourceJob = Job.dao.findById(param.getId());
        Task task = queryById(resourceJob.getTaskId());

        // 根据类型处理
        if (task.getType() == Constant.TaskType.TOGETHER) {
            jobList.addAll(Job.dao.find("select * from job where code = ?", resourceJob.getCode()));
        } else {
            jobList.add(Job.dao.findById(param.getId()));
        }

        for (Job job : jobList) {

            // 修改job字段
            job.setStatus(Constant.JOB_STATUS.FINISHED);
            job.setUpdatedBy(userId);
            job.setFinishTime(new Date());
            job.setFinishAmount(param.getFinishAmount());
            job.setDesc(param.getDesc());
            job.update();

            // 处理分数
            userService.addScore(job.getUserId(), task.getScore(), param.getId());
        }

        return ResultFactory.success(null);
    }

    /**
     * 抢任务
     *
     * @param jobId  jobId
     * @return
     */
    @Before(Tx.class)
    public Result grabJob(int jobId) {

        synchronized (TaskService.class) {

            // 先查出这个任务
            Job job = Job.dao.findById(jobId);
            if (null == job || job.getStatus() != Constant.JOB_STATUS.TO_BE_GRAB) {
                return ResultFactory.createResult(Constant.ResultCode.MISS_JOB_GRAB);
            }

            // 抢到这个任务
            job.setStatus(Constant.JOB_STATUS.RUNNING).update();
            // 删除其他人的这个任务
            Db.update("delete from job where code = ? and id != ? ", job.getCode(), job.getId());
            return ResultFactory.success(null);
        }
    }

    /**
     * 设置job过期
     */
    public void setJobsExpire() {
        List<Record> records = Db.find("select t.id, t.created_time, t1.expire_type from job t left join task t1 on t.task_id = t1.id where t.status in ("
                + Constant.JOB_STATUS.RUNNING + "," + Constant.JOB_STATUS.TO_BE_GRAB + ")");
        long now = new Date().getTime();
        List<Integer> expireIdList = new ArrayList<>();
        for (Record record : records) {
            Date expireDate = DateUtil.getTaskExpireDate(record.getDate("created_time"), record.getInt("expire_type"));
            if (expireDate.getTime() <= now) {
                expireIdList.add(record.get("id"));
            }
        }
        if (expireIdList.size() > 0) {
            Db.update("update job set status = ? where id in (" + StringUtils.join(expireIdList.toArray(), ",") + ")", Constant.JOB_STATUS.EXPIRED);
        }

        LogUtil.LogType.taskLog.info("{} jobs was expired", expireIdList.size());
    }
}
