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
        public static final int LEAK_PARAM = 1003;
        public static final int PASSWD_INCORRECT = 1004;
        public static final int PASSWD_LENGTH_ERR = 1005;
        public static final int MD5_ERR = 1006;
        public static final int FIRST_LOGIN = 1007;
        public static final int ILLEGAL_TOKEN = 1008;
        public static final int TOKEN_TIMEOUT = 1009;
        public static final int USER_DONOT_EXIST = 1009;
        public static final int USER_INACTIVE = 1010;
        public static final int DUPLICATE_RESOURCE = 1011;
    }

    static {
        codeMap.put(ResultCode.SUCCESS, "success");
        codeMap.put(ResultCode.ERROR, "system error");
        codeMap.put(ResultCode.LOGIN_FAIL, "用户名或密码错误");
        codeMap.put(ResultCode.HAVE_NOT_LOGIN, "用户未登陆");
        codeMap.put(ResultCode.LEAK_PARAM, "缺少参数");
        codeMap.put(ResultCode.PASSWD_INCORRECT, "密码错误");
        codeMap.put(ResultCode.PASSWD_LENGTH_ERR, "密码长度在6-16之间");
        codeMap.put(ResultCode.MD5_ERR, "MD5加密异常");
        codeMap.put(ResultCode.FIRST_LOGIN, "首次登陆");
        codeMap.put(ResultCode.ILLEGAL_TOKEN, "非法token");
        codeMap.put(ResultCode.TOKEN_TIMEOUT, "token 过期");
        codeMap.put(ResultCode.USER_DONOT_EXIST, "用户不存在");
        codeMap.put(ResultCode.USER_INACTIVE, "用户不可用");
        codeMap.put(ResultCode.DUPLICATE_RESOURCE, "存在相同的资源");
    }

    /* 任务类型 1-个人 2-家庭共同 3-家庭竞争 */
    public static class TaskType {
        public static final int PERSONAL = 1;
        public static final int FAMILY_TOGETHER = 2;
        public static final int FAMILY_COMPETE = 3;
    }

    public static class CACHE_KEY {
        public static final String USER_RESOURCE = "user_resource";
        public static final String USER_BASIC = "user_basic";
        public static final String FAMILY_BASIC = "family_basic";
    }

    /**
     * 资源类型
     */
    public static class RESOURCE_TYPE {
        public static final int MENU = 1;
        public static final int BUTTON = 2;
        public static final int DATA = 3;
        public static final int DATA_COMMON = 4;
    }

    /* 生效状态任务 */
    public static final int TASK_ACTIVE = 1;
    /* 放在JobDataMap中的task信息的key */
    public static final String KEY_TASK_INFO = "TASK_INFO";

}

