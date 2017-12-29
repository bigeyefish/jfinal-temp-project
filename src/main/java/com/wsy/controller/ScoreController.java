package com.wsy.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.wsy.model.User;
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
        Kv kv = getSessionAttr("userInfo");
        renderJson(userService.queryScoreRecord(((User)kv.get("user")).getFamilyId(), getParaMap(), getParaToInt(0), getParaToInt(1)));
    }
}
