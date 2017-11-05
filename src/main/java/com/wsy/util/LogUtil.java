package com.wsy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 日志管理器
 * Created by wangshiyu on 2015/12/1.
 */
public class LogUtil {

    /**
     * 缓存log4j日志名称
     */
    public static class LogType {

        /* 错误日志 */
        public static final Logger errorLog = LogManager.getLogger("error");

        /* 任务相关日期 */
        public static final Logger taskLog = LogManager.getLogger("task");
    }
}
