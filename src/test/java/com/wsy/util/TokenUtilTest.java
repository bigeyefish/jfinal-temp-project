package com.wsy.util;

import com.jfinal.kit.PropKit;

/**
 * Created by Lenovo on 2017/11/12.
 */
public class TokenUtilTest {

    static {
        PropKit.use("config.properties");
    }

    @org.junit.Test
    public void generateToken() throws Exception {
//        String s = TokenUtil.generateToken(1, "admin");
//        System.out.println(EncryptUtil.decrypt(s));
    }

}