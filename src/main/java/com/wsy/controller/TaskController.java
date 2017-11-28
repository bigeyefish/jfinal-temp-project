package com.wsy.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.Kv;
import com.wsy.model.Task;
import com.wsy.model.User;
import com.wsy.service.TaskService;

/**
 * Created by Lenovo on 2017/11/19.
 */
public class TaskController extends Controller{

    private TaskService taskService;

    public TaskController() {
        taskService = new TaskService();
    }

    /**
     * 查询家庭任务列表
     */
    public void queryFamilyTask() {
        Kv kv = getSessionAttr("userInfo");
        renderJson(taskService.queryFamilyTask(((User)kv.get("user")).getFamilyId(), getParaToInt(0), getParaToInt(1)));
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
        System.out.println(task);
    }
}
