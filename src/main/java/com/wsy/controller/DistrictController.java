package com.wsy.controller;

import com.jfinal.core.Controller;
import com.wsy.service.DistrictService;

/**
 * Created by Lenovo on 2018/1/16.
 */
public class DistrictController extends Controller {

    private DistrictService service = new DistrictService();

    /**
     * 查询小区数据
     */
    public void queryDistrict() {
        renderJson(service.queryAllDistrict());
    }
}
