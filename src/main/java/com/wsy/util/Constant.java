package com.wsy.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2017/10/15.
 */
public class Constant {

    /*返回数据编码和信息*/
    public static final Map<Integer, String> codeMap = new HashMap<>();

    public static final String MD5_FRE = "*_*ILovePotato*_*";

    public static class ResultCode {
        public static final int SUCCESS = 0;
        public static final int ERROR = -1;
        public static final int LOGIN_FAIL = 1001;
        public static final int HAVE_NOT_LOGIN = 1002;
        public static final int LEAD_PARAM = 1003;
        public static final int PASSWD_INCORRECT = 1004;
        public static final int PASSWD_LENGTH_ERR = 1005;
        public static final int MD5_ERR = 1006;
    }

    static {
        codeMap.put(ResultCode.SUCCESS, "success");
        codeMap.put(ResultCode.ERROR, "system error");
        codeMap.put(ResultCode.LOGIN_FAIL, "用户名或密码错误");
        codeMap.put(ResultCode.HAVE_NOT_LOGIN, "用户未登陆");
        codeMap.put(ResultCode.LEAD_PARAM, "缺少参数");
        codeMap.put(ResultCode.PASSWD_INCORRECT, "密码错误");
        codeMap.put(ResultCode.PASSWD_LENGTH_ERR, "密码长度在6-16之间");
        codeMap.put(ResultCode.MD5_ERR, "MD5加密异常");
    }
}
