package com.wsy.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.Kv;
import com.wsy.model.Job;
import com.wsy.model.Task;
import com.wsy.model.User;
import com.wsy.service.TaskService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2017/11/19.
 */
public class TaskController extends Controller{

    private TaskService taskService;

    public TaskController() {
        taskService = enhance(TaskService.class);
    }

    /**
     * 查询家庭任务列表
     */
    public void queryFamilyTask() {
        Kv kv = getSessionAttr("userInfo");
        renderJson(taskService.queryFamilyTask(((User)kv.get("user")).getFamilyId(), getParaToInt(0), getParaToInt(1)));
    }

    /**
     * 查询家庭task (k, v)
     */
    public void queryFamilyTaskForMap() {
        Kv kv = getSessionAttr("userInfo");
        renderJson(taskService.queryFamilyTaskForMap(((User)kv.get("user")).getFamilyId()));
    }

    /**
     * 查询当前登录人job列表
     */
    public void queryMyJob() {
        renderJson(taskService.queryJobByUserId(getSessionAttr("userId"), getParaToInt(0), getParaToInt(1), getParaMap()));
    }

    /**
     * 校验表达式是否合法
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void validateCron() {
        renderJson(taskService.validateCron(getPara("cronStr")));
    }

    /**
     * 保存任务
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void saveTask() {
        Task task = getBean(Task.class, "", true);
        List<Integer> ids = new ArrayList<>();
        for (String id : getPara("executorList").split(",")) {
            ids.add(Integer.parseInt(id));
        }
        renderJson(taskService.save(task, ids, getSessionAttr("userId")));
    }

    /**
     * 删除任务
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void deleteTask() {
        renderJson(taskService.deleteTask(getParaToInt("taskId")));
    }

    /**
     * 完成任务
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void finishJob() {
        renderJson(taskService.finishJob(getBean(Job.class, ""), getSessionAttr("userId")));
    }

    /**
     * 抢任务
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void grabJob() {
        renderJson(taskService.grabJob(getParaToInt(0)));
    }
}
