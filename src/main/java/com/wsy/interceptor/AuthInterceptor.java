package com.wsy.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.wsy.util.Constant;
import com.wsy.util.ResultFactory;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class AuthInterceptor implements Interceptor {
    public void intercept(Invocation inv) {
        Object user = inv.getController().getSessionAttr("user");
        if (null != user) {
            inv.invoke();
        } else {
            inv.getController().renderJson(ResultFactory.createResult(Constant.ResultCode.NOT_LOGIN, null));
        }
    }
}
