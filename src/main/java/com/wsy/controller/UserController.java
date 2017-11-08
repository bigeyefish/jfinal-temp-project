package com.wsy.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PropKit;
import com.wsy.interceptor.AuthInterceptor;
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
    @Clear({AuthInterceptor.class, GET.class})
    @Before(POST.class)
    public void login() {
        User user = userService.getUserByName(getPara("userName"));

        if (null != user && user.get("password").equals(getPara("password"))) {
            log.warn("用户[{}]登录成功", user.getUserName());
            setSessionAttr("user", user);
            user.setLastLogin(new Date()).update();
            Kv kv = Kv.by("userName", user.getUserName()).set("nickName", user.getNickName()).set("id", user.getId()).set("idNum", user.getIdNum()).set("tel", user.getTel());
            renderJson(ResultFactory.success(kv));
            return;
        }
        log.warn("用户[{}]登录失败", getPara("userName"));
        removeSessionAttr("user");
        renderJson(ResultFactory.createResult(Constant.ResultCode.LOGIN_FAIL, null));
    }

    /**
     * 修改密码
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void modifyPassword() {
        String oldPass = getPara("oldPass");
        String newPass = getPara("newPass");
        Integer userId = ((User) getSessionAttr("user")).getId();
        renderJson(userService.modifyPassword(userId, oldPass, newPass));
    }
}
