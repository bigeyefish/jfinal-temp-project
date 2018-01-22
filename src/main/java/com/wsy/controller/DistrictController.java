package com.wsy.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.wsy.interceptor.AuthInterceptor;
import com.wsy.service.DistrictService;

/**
 * Created by Lenovo on 2018/1/16.
 */
public class DistrictController extends Controller {

    private DistrictService service = new DistrictService();

    /**
     * 查询小区数据
     */
    @Clear({AuthInterceptor.class})
    public void queryDistrict() {
        renderJson(service.queryAllDistrict());
    }
}
