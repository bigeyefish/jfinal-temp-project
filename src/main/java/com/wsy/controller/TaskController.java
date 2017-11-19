package com.wsy.controller;

import com.jfinal.core.Controller;
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
        renderJson(taskService.queryFamilyTask(getSessionAttr("userId"), getParaToInt(0), getParaToInt(1)));
    }
}
