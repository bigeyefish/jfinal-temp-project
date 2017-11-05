package com.wsy.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2017/10/15.
 */
public class Constant {

    /*返回数据编码和信息*/
    public static final Map<Integer, String> codeMap = new HashMap<>();

    static {
        codeMap.put(ResultCode.SUCCESS, "success");
        codeMap.put(ResultCode.ERROR, "system error");
        codeMap.put(ResultCode.LOGIN_FAIL, "用户名或密码错误");
    }

    public static class ResultCode {
        public static final int SUCCESS = 0;
        public static final int ERROR = -1;
        public static final int LOGIN_FAIL = 1001;
    }

    /* 任务类型 1-个人 2-家庭共同 3-家庭竞争 */
    public static class TaskType {
        public static final int PERSONAL = 1;
        public static final int FAMILY_TOGETHER = 2;
        public static final int FAMILY_COMPETE = 3;

    }

    /* 生效状态任务 */
    public static final int TASK_ACTIVE = 1;
    /* 放在JobDataMap中的task信息的key */
    public static final String KEY_TASK_INFO = "TASK_INFO";
}
