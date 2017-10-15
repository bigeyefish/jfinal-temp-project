package com.wsy;

import com.jfinal.config.*;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.wsy.interceptor.AuthInterceptor;
import com.wsy.model.db.User;
import com.wsy.routes.AdminRoutes;
import com.wsy.routes.FrontRoutes;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class MyConfig extends JFinalConfig {
    public void configConstant(Constants me) {
        // 第一次使用use加载的配置将成为主配置，可以通过PropKit.get(...)直接取值
        PropKit.use("config.properties");
        me.setDevMode(PropKit.getBoolean("devMode"));
    }

    public void configRoute(Routes me) {
        me.setBaseViewPath("/view");
        me.add(new FrontRoutes());  // 前端路由
        me.add(new AdminRoutes());  // 后端路由
    }

    public void configEngine(Engine me) {

    }

    public void configPlugin(Plugins me) {
        // 非第一次使用use加载的配置，需要通过每次使用use来指定配置文件名再来取值
//        loadPropertyFile("conifg.properties");
        Prop p = PropKit.use("db_config.properties");
        DruidPlugin dp = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setDialect(new MysqlDialect());
        arp.setBaseSqlTemplatePath(PathKit.getRootClassPath());
        arp.addSqlTemplate("temp.sql");
        arp.addMapping("user", User.class);
        me.add(arp);
    }

    public void configInterceptor(Interceptors me) {
        me.addGlobalActionInterceptor(new AuthInterceptor());
    }

    public void configHandler(Handlers me) {
//        me.add(new ResourceHandler());
    }
}
