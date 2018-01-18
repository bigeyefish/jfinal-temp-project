package com.wsy.util;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.ext.kit.DateKit;
import com.jfinal.kit.PropKit;
import com.wsy.model.biz.Result;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码
 * Created by sanyihwang on 2018/1/18.
 */
public class SmsUtil {

    /* 存储未交验过的短信验证码，供校验使用 */
    private static Map<String, String> smsCodeMap = new HashMap<>();

    /**
     * 发送验证码
     * @param tel 手机号码
     * @param code 数据验证码
     * @return result
     */
    public static Result sendSms(String tel, String code) {
        JSONObject json = new JSONObject();
        JSONObject smsJson = new JSONObject();
        smsJson.put("code", code);
        try {
            String uid = PropKit.get("sms.uid");
            String time = DateKit.toStr(new Date(), "yyyyMMddHHmmss");
            String ckey = MD5Encoder.encode(uid + "-" + time + "-send-" + PropKit.get("sms.key")).toUpperCase();
            json.put("uid", uid);
            json.put("timeStamp", time);
            json.put("parameters", smsJson.toJSONString());
            json.put("modelId", PropKit.get("sms.modelId"));
            json.put("mobile", tel);
            json.put("ckey", ckey);
            String result = HttpUtil.postJson(PropKit.get("sms.server"), json);
            System.out.println(json.toJSONString());
            System.out.println(result);
            return ResultFactory.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.error(e.getMessage());
        }
    }

    /**
     * 验证短信验证码
     * @param tel 手机号
     * @param smsCode 短信验证码
     * @return
     */
    public static boolean verifySms(String tel, String smsCode) {
        if (null != smsCodeMap.get(tel) && smsCodeMap.get(tel).equalsIgnoreCase(smsCode)) {
            smsCodeMap.remove(tel);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        PropKit.use("config.properties");
        SmsUtil.sendSms("13971256024", "123456");
    }
}
