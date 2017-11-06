package com.wsy.util;

import com.jfinal.kit.PropKit;
import com.wsy.model.Interviewer;
import com.wsy.model.biz.Result;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sanyihwang on 2017/11/6.
 */
public class FalconsUtilTest {

    static {
        PropKit.use("config.properties");
    }

    @Test
    public void reportData() throws Exception {

        Interviewer interviewer = new Interviewer();
        interviewer.setTel("13812345678");
        interviewer.setIdNum("420000198702154652");
        interviewer.setName("张三");

        Result result = FalconsUtil.reportData(interviewer);
        System.out.println(result);
    }
}