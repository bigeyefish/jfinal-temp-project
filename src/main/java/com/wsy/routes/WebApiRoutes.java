package com.wsy.routes;

import com.jfinal.config.Routes;
import com.wsy.controller.ResourceController;
import com.wsy.controller.ScoreController;
import com.wsy.controller.TaskController;
import com.wsy.controller.UserController;
import com.wsy.interceptor.WebAuthInterceptor;

/**
 * web页面api路由
 * Created by Lenovo on 2017/10/13.
 */
public class WebApiRoutes extends Routes {
    public void config() {
        addInterceptor(new WebAuthInterceptor());
        add("/api/user", UserController.class);
        add("/api/resource", ResourceController.class);
        add("/api/task", TaskController.class);
        add("/api/score", ScoreController.class);
    }
}
