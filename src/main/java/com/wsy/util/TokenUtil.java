package com.wsy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by Lenovo on 2017/11/12.
 */
public class TokenUtil {

    public static final Logger log = LogManager.getLogger(TokenUtil.class);

    private static final String TOKEN_SEP_SIGN = "-";

    /**
     * 生成token字符串
     * @param id
     * @param userName
     * @return
     */
    public static String generateToken(int id, String userName) {
        return EncryptUtil.encrypt(id + TOKEN_SEP_SIGN + userName + TOKEN_SEP_SIGN + new Date().getTime());
    }

    /**
     * 从token中解析出数据
     * @param token
     * @return
     */
    public static String[]  decodeToken(String token) {
        String key = EncryptUtil.decrypt(token);
        return key == null ? null : key.split(TOKEN_SEP_SIGN);
    }
}
