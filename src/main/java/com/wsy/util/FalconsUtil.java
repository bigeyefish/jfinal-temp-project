package com.wsy.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.ext.kit.DateKit;
import com.jfinal.kit.PropKit;
import com.wsy.model.Interviewer;
import com.wsy.model.biz.Result;

import java.io.IOException;
import java.util.Date;

/**
 * 猎鹰平台
 * Created by sanyihwang on 2017/11/6.
 */
public class FalconsUtil {

    /**
     * 上报访客数据
     * @param interviewer
     * @param imgBase64
     */
    public static Result reportData(Interviewer interviewer, String imgBase64) {
        JSONObject map = new JSONObject();
        map.put("name", interviewer.getName());
        map.put("sex", interviewer.getSex());
        map.put("age", interviewer.getAge());
        map.put("card", interviewer.getIdNum());
        map.put("phone", interviewer.getTel());
        map.put("time", DateKit.toStr(new Date()));
        map.put("token", PropKit.get("falcons.token"));
        map.put("image", imgBase64);
        try {
            String result = HttpUtil.postJson(PropKit.get("falcons.url"), map);
            JSONObject jsonObject = (JSONObject) JSON.parse(result);
            if (null != jsonObject.get("code")) {
                return new Result(Integer.parseInt((String)jsonObject.get("code")), (String)jsonObject.get("message"), null);
            }
            return ResultFactory.error(null);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultFactory.error(null);
        }
    }
}
