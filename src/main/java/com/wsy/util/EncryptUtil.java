package com.wsy.util;

import com.jfinal.kit.Base64Kit;
import com.jfinal.kit.PropKit;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Lenovo on 2017/11/8.
 */
public class EncryptUtil {

    private static String key = PropKit.get("token.encrypt.key");


    /**
     * MD5加密
     * @param string
     * @return
     */
    public static String getMD5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * AES 加密
     * @param str 待加密的字符串
     * @return
     */
    public static String encrypt(String str) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("utf-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); //"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(str.getBytes("utf-8"));
            return Base64Kit.encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES 解密
     * @param str
     * @return
     */
    public static String decrypt(String str) {
        try {
            byte[] raw = key.getBytes("utf-8");
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return new String(cipher.doFinal(Base64Kit.decode(str)), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
