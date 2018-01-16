package com.wsy.service;

import com.wsy.model.District;
import com.wsy.model.biz.Result;
import com.wsy.util.ResultFactory;

/**
 * Created by Lenovo on 2018/1/16.
 */
public class DistrictService {

    /**
     * 获取小区数据
     * @return
     */
    public Result queryAllDistrict() {
        try {
            return ResultFactory.success(District.dao.find("select * from district"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.error(e.getMessage());
        }
    }

    /**
     * 根据id查询小区数据
     * @param id
     * @return
     */
    public District queryById(int id) {
        try {
            return District.dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
