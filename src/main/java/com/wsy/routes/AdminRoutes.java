package com.wsy.routes;

import com.jfinal.config.Routes;
import com.wsy.controller.UserController;
import com.wsy.interceptor.AdminInterceptor;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class AdminRoutes extends Routes {
    public void config() {
        addInterceptor(new AdminInterceptor());
        add("/api/user", UserController.class);
    }
}
