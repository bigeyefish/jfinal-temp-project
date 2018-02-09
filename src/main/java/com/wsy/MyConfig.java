package com.wsy;

import com.jfinal.config.*;
import com.jfinal.json.FastJsonFactory;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;
import com.wsy.Handler.ResourceHandler;
import com.wsy.controller.ProxyController;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class MyConfig extends JFinalConfig {
    public void configConstant(Constants me) {
        // 第一次使用use加载的配置将成为主配置，可以通过PropKit.get(...)直接取值
        PropKit.use("config.properties");
        me.setDevMode(PropKit.getBoolean("devMode"));

        me.setEncoding("utf-8");
        me.setJsonFactory(new FastJsonFactory());

    }

    public void configRoute(Routes me) {
        me.add("/ideality", ProxyController.class);
    }

    public void configEngine(Engine me) {

    }

    public void configPlugin(Plugins me) {
    }

    public void configInterceptor(Interceptors me) {
    }

    public void configHandler(Handlers me) {
        me.add(new ResourceHandler());
    }
}
