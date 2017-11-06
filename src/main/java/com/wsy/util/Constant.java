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
    }

    static {
        codeMap.put(ResultCode.SUCCESS, "success");
        codeMap.put(ResultCode.ERROR, "system error");
        codeMap.put(ResultCode.LOGIN_FAIL, "用户名或密码错误");
        codeMap.put(ResultCode.DECODE_CARD_ERR, "解析电子卡包数据异常");
    }
}
