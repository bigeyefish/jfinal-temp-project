package com.wsy.routes;

import com.jfinal.config.Routes;
import com.wsy.controller.UserController;
import com.wsy.interceptor.WechartAuthInterceptor;

/**
 * 微信请求路由
 * Created by Lenovo on 2017/10/13.
 */
public class WechartRoutes extends Routes {
    public void config() {
        addInterceptor(new WechartAuthInterceptor());
        add("wx/api/user", UserController.class);
    }
}
