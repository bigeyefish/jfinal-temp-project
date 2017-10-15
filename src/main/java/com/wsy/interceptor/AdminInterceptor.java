package com.wsy.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class AdminInterceptor implements Interceptor {
    public void intercept(Invocation inv) {
        inv.invoke();
    }
}
