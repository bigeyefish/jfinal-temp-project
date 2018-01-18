package com.wsy.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.wsy.interceptor.AuthInterceptor;
import com.wsy.util.SmsUtil;

/**
 * 短信验证码
 * Created by sanyihwang on 2018/1/18.
 */
public class SmsController extends Controller {

    @Clear({AuthInterceptor.class, GET.class})
    @Before(POST.class)
    public void sendSms() {
        renderJson(SmsUtil.sendSms(getPara("tel"), getPara("code")));
    }
}
