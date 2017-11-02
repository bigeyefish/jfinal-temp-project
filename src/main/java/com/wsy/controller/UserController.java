package com.wsy.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.wsy.model.User;
import com.wsy.service.UserService;
import com.wsy.util.Constant;
import com.wsy.util.ResultFactory;

import java.util.Date;

/**
 * the controller witch relationship on user
 * Created by Lenovo on 2017/10/13.
 */
public class UserController extends Controller{

    private UserService userService = new UserService();

    /**
     * 登录
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void login() {
        User user = userService.getUserByName(getPara("userName"));

        if (null != user && user.get("password").equals(getPara("password"))) {
            setSessionAttr("user", user);
            user.setLastLogin(new Date()).update();
            renderJson(ResultFactory.success(null));
            return;
        }
        renderJson(ResultFactory.createResult(Constant.ResultCode.LOGIN_FAIL, null));
    }
}
