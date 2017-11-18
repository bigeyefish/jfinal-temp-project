package com.wsy.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.wsy.util.ResultFactory;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class AdminInterceptor implements Interceptor {
    public void intercept(Invocation inv) {
        try {
            inv.invoke();
        } catch (Exception e) {
            e.printStackTrace();
            inv.getController().renderJson(ResultFactory.error(e.getMessage()));
        }
    }
}
