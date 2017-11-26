package com.wsy.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.wsy.model.User;
import com.wsy.model.biz.Result;
import com.wsy.service.UserService;
import com.wsy.util.Constant;
import com.wsy.util.TokenUtil;

import javax.servlet.http.HttpSession;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class AuthInterceptor implements Interceptor {

    private UserService userService = new UserService();

    public void intercept(Invocation inv) {
        Result result = TokenUtil.validate(inv.getController().getCookie("token"));
        if (result.getCode() != Constant.ResultCode.SUCCESS) {
            if (result.getCode() == Constant.ResultCode.TOKEN_TIMEOUT) {
                inv.getController().getResponse().setStatus(401);
                inv.getController().removeCookie("token");
            }
            inv.getController().renderJson(result);
        } else {
            HttpSession session = inv.getController().getSession();
            session.setAttribute("userId", ((User)result.getData()).getId());
            if (null == session.getAttribute("userInfo")) {
                session.setAttribute("userInfo", userService.getUserInfo(((User)result.getData()).getId()).getData());
            }
            inv.invoke();
        }
    }
}
