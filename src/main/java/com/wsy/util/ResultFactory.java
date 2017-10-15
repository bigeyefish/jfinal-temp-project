package com.wsy.util;

import com.wsy.model.biz.Result;

/**
 * Created by Lenovo on 2017/10/15.
 */
public class ResultFactory {

    /**
     * 构建接口成功返回数据
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return ResultFactory.createResult(Constant.ResultCode.SUCCESS, data);
    }

    /**
     * 构建接口程序异常返回数据
     * @param data
     * @return
     */
    public static Result error(Object data) {
        return ResultFactory.createResult(Constant.ResultCode.ERROR, data);
    }

    /**
     * 构建接口返回数据
     * @param code 返回码
     * @param data 业务数据
     * @return
     */
    public static Result createResult(int code, Object data) {
        return new Result(code, Constant.codeMap.get(code), data);
    }
}
