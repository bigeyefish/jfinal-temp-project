package com.wsy.routes;

import com.jfinal.config.Routes;
import com.wsy.controller.IndexController;

/**
 * 前端路由
 * Created by Lenovo on 2017/10/13.
 */
public class FrontRoutes extends Routes {
    public void config() {
        add("/", IndexController.class);
    }
}
