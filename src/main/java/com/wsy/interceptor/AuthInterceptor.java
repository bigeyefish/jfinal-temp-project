package com.wsy.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.StrKit;
import com.wsy.model.User;
import com.wsy.util.Constant;
import com.wsy.util.ResultFactory;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class AuthInterceptor implements Interceptor {
    public void intercept(Invocation inv) {
        String userId = inv.getController().getPara("userId");
        if (StrKit.isBlank(userId) || null == User.dao.findById(Integer.parseInt(userId))) {
            inv.getController().renderJson(ResultFactory.createResult(Constant.ResultCode.LEAK_USER_ID, null));
        } else {
            inv.invoke();
        }
//        Object user = inv.getController().getSessionAttr("user");
//        if (null != user) {
//            inv.invoke();
//        } else {
//            inv.getController().renderJson(ResultFactory.createResult(Constant.ResultCode.NOT_LOGIN, null));
//        }
    }
}
