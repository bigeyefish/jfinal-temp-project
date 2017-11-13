package com.wsy.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.StrKit;
import com.wsy.util.Constant;
import com.wsy.util.ResultFactory;
import com.wsy.util.TokenUtil;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class AuthInterceptor implements Interceptor {
    public void intercept(Invocation inv) {
        String token = inv.getController().getCookie("token");
        if (StrKit.isBlank(token) || TokenUtil.decodeToken(token) == null) {
            inv.getController().renderJson(ResultFactory.createResult(Constant.ResultCode.HAVE_NOT_LOGIN, null));
        } else {
            inv.invoke();
        }
    }
}
