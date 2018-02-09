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
import com.wsy.service.DistrictService;
import com.wsy.service.UserService;
import com.wsy.util.Constant;
import com.wsy.util.ResultFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Properties;

/**
 * the controller witch relationship on user
 * Created by Lenovo on 2017/10/13.
 */
public class UserController extends Controller{

    private UserService userService = new UserService();
    private DistrictService districtService = new DistrictService();
    public static final Logger log = LogManager.getLogger(UserController.class);

    /**
     * 登录
     */
    @Clear({AuthInterceptor.class, GET.class})
    @Before(POST.class)
    public void login() {
        User user = userService.getUserByTel(getPara("tel"));

        if (null != user && user.get("password").equals(getPara("password"))) {
            log.warn("用户[{}]登录成功", user.getUserName());
            setSessionAttr("user", user);
            user.setLastLogin(new Date()).update();

            // 系统配置
            Properties properties = PropKit.use("open.properties").getProperties();
            Kv kv = Kv.by("userName", user.getUserName()).set("id", user.getId())
                    .set("idNum", user.getIdNum()).set("tel", user.getTel()).set("sysConf", properties);
            kv.set("district", districtService.queryById(user.getDistrictId()));

            renderJson(ResultFactory.success(kv));
            return;
        }
        log.warn("用户[{}]登录失败", getPara("tel"));
        removeSessionAttr("user");
        renderJson(ResultFactory.createResult(Constant.ResultCode.LOGIN_FAIL, null));
    }

    /**
     * 修改密码
     */
    @Clear({AuthInterceptor.class, GET.class})
    @Before(POST.class)
    public void resetPassword() {
        String tel = getPara("tel");
        String newPass = getPara("newPass");
        String token = getPara("token");
        renderJson(userService.resetPassword(tel, newPass, token));
    }

    /**
     * 用户注册
     */
    @Clear({AuthInterceptor.class, GET.class})
    @Before(POST.class)
    public void regist() {
        renderJson(userService.addUser(getBean(User.class), getPara("token")));
    }
}
