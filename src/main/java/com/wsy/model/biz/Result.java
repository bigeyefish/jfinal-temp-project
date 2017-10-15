package com.wsy.model.biz;

/**
 * api返回结果
 * Created by Lenovo on 2017/10/15.
 */
public class Result {

    /*返回状态码*/
    private int code;

    /*返回信息*/
    private String msg;

    /*返回数据*/
    private Object data;

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
