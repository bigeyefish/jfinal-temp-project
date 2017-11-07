package com.wsy.util;
import cn.net.mycards.qrcode.Parser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.wsy.model.Interviewer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * 电子卡包工具类
 * Created by sanyihwang on 2017/11/6.
 */
public class CardUtil {

    private static String[] area = new String[]{ "APP_name","APP_idNum","APP_tel" };
    public static final Logger log = LogManager.getLogger(CardUtil.class);


    /**
     * 从json字符串中解析出卡信息
     * @param jsonStr
     */
    public static Interviewer decodeCardInfo(String jsonStr) {

        try {
            String info = Parser.getInfo(jsonStr, area, 0, null, PropKit.get("card.uid"), PropKit.get("card.enckey"), PropKit.get("card.hashkey"));
            JSONObject jsonObject = (JSONObject) JSON.parse(info);
            String resultCode = (String)jsonObject.get("result_code");
            if (null != resultCode) {
                String resultMsg = (String)jsonObject.get("result_msg");
                log.error("解析电子卡包身份信息异常：code:[{}], msg: [{}]", resultCode, resultMsg);
                return null;
            }
            JSONObject jsonObj = (JSONObject) jsonObject.get("APP");
            Interviewer interviewer = new Interviewer();
            interviewer.setName((String)jsonObj.get("name"));
            interviewer.setTel((String)jsonObj.get("tel"));
            interviewer.setIdNum((String)jsonObj.get("idNum"));
            return interviewer;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("解析电子卡包身份信息报错：{}", e.getMessage());
            return null;
        }
    }
}
