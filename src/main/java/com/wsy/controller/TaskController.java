package com.wsy.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
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
}
