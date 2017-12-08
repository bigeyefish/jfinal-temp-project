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

    public static final String PROP_SEP_SIGN = "@@";

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
        public static final int USER_DONOT_EXIST = 1010;
        public static final int USER_INACTIVE = 1011;
        public static final int DUPLICATE_RESOURCE = 1012;
        public static final int ILLEGAL_CRON = 1013;
        public static final int DELETE_QUARTZ_JOB_ERROR = 1014;
        public static final int SCORE_NOT_ENOUGH = 1015;
        public static final int MISS_JOB_GRAB = 1016;
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
        codeMap.put(ResultCode.FIRST_LOGIN, "未登陆");
        codeMap.put(ResultCode.ILLEGAL_TOKEN, "非法token");
        codeMap.put(ResultCode.TOKEN_TIMEOUT, "token 过期");
        codeMap.put(ResultCode.USER_DONOT_EXIST, "用户不存在");
        codeMap.put(ResultCode.USER_INACTIVE, "用户不可用");
        codeMap.put(ResultCode.DUPLICATE_RESOURCE, "存在相同的资源");
        codeMap.put(ResultCode.ILLEGAL_CRON, "cron表达式非法");
        codeMap.put(ResultCode.DELETE_QUARTZ_JOB_ERROR, "删除quartz中job失败");
        codeMap.put(ResultCode.SCORE_NOT_ENOUGH, "剩余分值不足");
        codeMap.put(ResultCode.MISS_JOB_GRAB, "任务已经被别人抢走，你手慢了");
    }

    /* 任务类型 1-个人 2-竞争 3- 共同 4轮流*/
    public static class TaskType {
        public static final int PERSONAL = 1;
        public static final int COMPETE = 2;
        public static final int TOGETHER = 3;
        public static final int BYTURN = 4;
    }

    public static class CACHE_KEY {
        public static final String USER_RESOURCE = "user_resource";
        public static final String USER_BASIC = "user_basic";
        // 带敏感信息（token、password）的用户基本信息
        public static final String USER_BASIC_SEC = "user_basic_sec";
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

    /* 放在JobDataMap中的task信息的key */
    public static final String KEY_TASK_ID = "TASK_ID";

    /* task状态（根据起止时间计算）*/
    public static class TASK_STATUS {
        public static final String NOT_START = "未启动";
        public static final String RUNNING = "运行中";
        public static final String END = "已结束";
    }

    /* job状态 运行中、已完成、已过期、已下线、待抢 */
    public static class JOB_STATUS {
        public static final int RUNNING = 1;
        public static final int FINISHED = 2;
        public static final int EXPIRED = 3;
        public static final int TO_BE_GRAB = 4;
        public static final int OFF_LINE  = 5;
    }

    /* 延期类型 - 本日、本周、一周、本月 */
    public static class EXPIRE_TYPE {
        public static final int CUR_DAY = 1;
        public static final int CUR_WEEK = 2;
        public static final int WEEK_DAYS = 3;
        public static final int CUR_MONTH = 4;
    }
}

