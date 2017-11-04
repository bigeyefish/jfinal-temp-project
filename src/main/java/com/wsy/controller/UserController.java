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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * the controller witch relationship on user
 * Created by Lenovo on 2017/10/13.
 */
public class UserController extends Controller{

    private UserService userService = new UserService();
    public static final Logger log = LogManager.getLogger(UserController.class);

    /**
     * 登录
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void login() {
        User user = userService.getUserByName(getPara("userName"));

        if (null != user && user.get("password").equals(getPara("password"))) {
            log.warn("用户[{}]登录成功", user.getUserName());
            setSessionAttr("user", user);
            user.setLastLogin(new Date()).update();
            renderJson(ResultFactory.success(null));
            return;
        }
        log.warn("用户[{}]登录失败", getPara("userName"));
        renderJson(ResultFactory.createResult(Constant.ResultCode.LOGIN_FAIL, null));
    }
}
