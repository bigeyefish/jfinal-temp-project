package com.wsy.routes;

import com.jfinal.config.Routes;
import com.wsy.controller.DistrictController;
import com.wsy.controller.IndexController;
import com.wsy.controller.InterviewController;
import com.wsy.controller.SmsController;

/**
 * 前端路由
 * Created by Lenovo on 2017/10/13.
 */
public class FrontRoutes extends Routes {
    public void config() {
        add("/", IndexController.class);
        add("/api/interview", InterviewController.class);
        add("/api/district", DistrictController.class);
        add("/api/sms", SmsController.class);
    }
}
