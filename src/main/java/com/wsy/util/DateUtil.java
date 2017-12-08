package com.wsy.util;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sanyihwang on 2017/12/5.
 */
public class DateUtil {

    /**
     * 判断两个date是否是相同时间 （如果都为null认为是相同）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        if (null == date1 && null == date2) {
            return true;
        }
        if (date1 != null && date2 != null) {
            return date1.getTime() == date2.getTime();
        } else {
            return false;
        }
    }

    /**
     * task有效期限计算
     *
     * @param createdDate task生成时间
     * @param expireType  task过期类型
     * @return 失效时间
     */
    public static Date getTaskExpireDate(Date createdDate, int expireType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        switch (expireType) {
            case Constant.EXPIRE_TYPE.CUR_DAY:
                return DateUtils.addDays(calendar.getTime(), 1);
            case Constant.EXPIRE_TYPE.CUR_MONTH:
                return DateUtils.ceiling(calendar.getTime(), Calendar.MONTH);
            case Constant.EXPIRE_TYPE.CUR_WEEK:
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                calendar.add(Calendar.DATE, 7);
                return calendar.getTime();
            case Constant.EXPIRE_TYPE.WEEK_DAYS:
                return DateUtils.addDays(calendar.getTime(), 7);
            default:
                return null;
        }
    }
}
