package com.wsy.util;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.ext.kit.DateKit;
import com.jfinal.kit.PropKit;
import com.wsy.model.biz.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码
 * Created by sanyihwang on 2018/1/18.
 */
public class SmsUtil {
    private static Logger logger = LogManager.getLogger(SmsUtil.class);

    /* 存储未交验过的短信验证码，供校验使用 */
    private static Map<String, String> smsCodeMap = new HashMap<>();

    /**
     * 发送验证码
     *
     * @param tel 手机号码
     * @return result
     */
    public static Result sendSms(String tel) {
        JSONObject json = new JSONObject();
        JSONObject smsJson = new JSONObject();

        // 生成随机数字
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
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
            // {"resCode":"0000","resMsg":"调用成功"}
            JSONObject retObj = (JSONObject) JSONObject.parse(result);
            logger.info("sms: param: [{}], result: [{}]", json.toJSONString(), result);
            if (retObj.getInteger("resCode") == 0) {
                smsCodeMap.put(tel, code);
                return ResultFactory.success(null);
            }
            return ResultFactory.error(retObj.get("resMsg"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.error(e.getMessage());
        }
    }

    /**
     * 验证短信验证码
     *
     * @param tel     手机号
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
}
