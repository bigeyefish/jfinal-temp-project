package com.wsy.controller;

import com.jfinal.core.Controller;
import com.wsy.service.UserService;

/**
 * 分数控制类
 * Created by Lenovo on 2017/12/28.
 */
public class ScoreController extends Controller {

    private UserService userService = new UserService();

    /**
     * 查询分数记录
     */
    public void queryScoreFlow() {
        renderJson(userService.queryScoreRecord(getParaMap(), getParaToInt(0), getParaToInt(1)));
    }
}
