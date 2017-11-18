package com.wsy.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.wsy.interceptor.AuthInterceptor;
import com.wsy.model.biz.Result;
import com.wsy.service.UserService;
import com.wsy.util.Constant;
import com.wsy.util.ResultFactory;
import com.wsy.util.TokenUtil;

/**
 * the controller witch relationship on user
 * Created by Lenovo on 2017/10/13.
 */
public class UserController extends Controller{

    private UserService userService = new UserService();

    /**
     * 登录
     */
    @Clear({GET.class, AuthInterceptor.class})
    @Before(POST.class)
    public void login() {
        Result result = ResultFactory.createResult(Constant.ResultCode.LEAK_PARAM);
        if (StrKit.notBlank(getPara("userName")) && StrKit.notBlank(getPara("password"))) {
            result = userService.logIn(getPara("userName"), getPara("password"));
        }
        if (result.getCode() == Constant.ResultCode.SUCCESS) {
            Kv kv = ((Kv) result.getData());
            setCookie("token", TokenUtil.generateToken(kv.getInt("id"), kv.getStr("userName")), PropKit.getInt("token.timeout"), true);
        } else {
            removeCookie("token");
        }
        renderJson(result);
    }

    /**
     * 获取用户信息（基本信息，资源信息）
     */
    public void getUserInfo() {
        renderJson(userService.getUserInfo(getSessionAttr("userId")));
    }

    /**
     * 修改密码
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void modifyPassword() {
        String oldPass = getPara("oldPass");
        String newPass = getPara("newPass");
        Integer userId = getSessionAttr("userId");
        renderJson(userService.modifyPassword(userId, oldPass, newPass));
    }
}
