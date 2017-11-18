package com.wsy.util;

import com.jfinal.kit.PropKit;
import org.junit.Test;

/**
 * Created by Lenovo on 2017/11/12.
 */
public class EncryptUtilTest {

    static {
        PropKit.use("config.properties");
    }

    @Test
    public void getMD5() throws Exception {
        String s = EncryptUtil.getMD5("*_*ILovePotato*_*admin123");
        System.out.println(s);
    }

}