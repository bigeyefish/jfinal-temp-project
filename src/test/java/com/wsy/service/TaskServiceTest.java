package com.wsy.service;

import org.junit.Test;
import org.quartz.CronExpression;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by sanyihwang on 2017/11/28.
 */
public class TaskServiceTest {
    @Test
    public void validateCron() throws Exception {
        Date d = new CronExpression("0 0 0 ? * 6L").getNextValidTimeAfter(new Date());
        System.out.println(d);
    }

}