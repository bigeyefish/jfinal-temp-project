package com.wsy.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2017/10/15.
 */
public class Constant {

    /*返回数据编码和信息*/
    public static final Map<Integer, String> codeMap = new HashMap<>();

    public static class ResultCode {
        public static final int SUCCESS = 0;
        public static final int ERROR = -1;
        public static final int LOGIN_FAIL = 1001;
        public static final int DECODE_CARD_ERR = 1002;
        public static final int LEAK_PARAM = 1003;
        public static final int NOT_LOGIN = 1004;
        public static final int PASSWORD_ERR = 1005;
        public static final int PASSWORD_CHECK_ERR = 1006;
        public static final int TEL_EXIST = 1007;
        public static final int TEL_NOT_EXIST = 1008;
        public static final int VERIFY_CODE_ERR = 1009;
    }

    static {
        codeMap.put(ResultCode.SUCCESS, "success");
        codeMap.put(ResultCode.ERROR, "system error");
        codeMap.put(ResultCode.LOGIN_FAIL, "用户名或密码错误");
        codeMap.put(ResultCode.DECODE_CARD_ERR, "解析电子卡包数据异常");
        codeMap.put(ResultCode.LEAK_PARAM, "缺少参数");
        codeMap.put(ResultCode.NOT_LOGIN, "用户未登录");
        codeMap.put(ResultCode.PASSWORD_ERR, "用户密码错误");
        codeMap.put(ResultCode.PASSWORD_CHECK_ERR, "密码在6-16个字符之间");
        codeMap.put(ResultCode.TEL_EXIST, "系统存在相同电话号码");
        codeMap.put(ResultCode.TEL_NOT_EXIST, "手机号码不存在");
        codeMap.put(ResultCode.VERIFY_CODE_ERR, "短信验证码错误");
    }
}
