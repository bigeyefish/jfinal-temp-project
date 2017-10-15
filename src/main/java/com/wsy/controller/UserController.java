package com.wsy.controller;

import com.jfinal.core.Controller;
import com.wsy.model.db.User;
import com.wsy.service.UserService;
import com.wsy.util.Constant;
import com.wsy.util.ResultFactory;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class UserController extends Controller{

    private UserService userService = new UserService();

    /**
     * 登录
     */
    public void login() {
        User user = userService.getUserByName(getPara("userName"));

        if (null != user && user.get("password").equals(getPara("password"))) {
            setSessionAttr("user", user);
            renderJson(ResultFactory.success(null));
        }
        renderJson(ResultFactory.createResult(Constant.ResultCode.LOGIN_FAIL, null));
    }
}
