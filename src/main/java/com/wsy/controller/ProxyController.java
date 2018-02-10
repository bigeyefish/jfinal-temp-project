package com.wsy.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.kit.PropKit;
import com.wsy.util.*;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2018/1/16.
 */
public class ProxyController extends Controller {

    public void redirectService() {
        if (null == getHeader("tokenId")) {
            renderJson(ResultFactory.error("缺少tokenId"));
            return;
        }
        if (getRequest().getMethod().equals("GET")) {
            String url = PropKit.get("remote.server.biz") + "?";
            Map<String, String[]> paraMap = getParaMap();
            for (String key : paraMap.keySet()) {
                url += key + "=" + paraMap.get(key)[0] + "&";
            }
            try {
                renderJson(HttpUtil.getJson(url.substring(0, url.length() - 1), getHeader("tokenId")));
                return;
            } catch (IOException e) {
                e.printStackTrace();
                renderJson(ResultFactory.error("中转程序请求服务异常["+ e.getMessage() +"]"));
            }
        }  else {
            String tokenId = getHeader("tokenId");
            List<NameValuePair> data = new ArrayList<>();

            if (tokenId.equals("50bda8bca3b543a59840f2dfdb8b498c")) {
                // 发送验证码
                renderJson(SmsUtil.sendSms(getPara("tel")));
                return;
            }

            if (tokenId.equals("ae83eaa38c9849dd84a828d95124deb5")) {
                // 电子卡包
                try {
                    // 保留编码的codeId
                    JSONObject json = (JSONObject)JSONObject.parse(getPara("data"));
                    json.put("cardId", json.getString("codeId"));

                    // 解析电子卡包数据
                    JSONObject jsonObj = CardUtil.decodeCardInfo(getPara("data"));
                    if (null == jsonObj) {
                        renderJson(ResultFactory.createResult(Constant.ResultCode.DECODE_CARD_ERR, null));
                        return;
                    }
                    json.put("name", jsonObj.get("name"));
                    json.put("tel", jsonObj.get("tel"));
                    json.put("idNum", jsonObj.get("idNum"));

                } catch (Exception e) {
                    e.printStackTrace();
                    renderJson(ResultFactory.error("中转程序解析电子卡包数据异常["+ e.getMessage() +"]"));
                }
            } else if (tokenId.equals("4c8c3f01550a4124928a2dcce746e7cd") || tokenId.equals("a1b3614b83df45deb54fce3ed7f8e038")) {
                // 重置密码 或 注册用户
                if (!SmsUtil.verifySms(getPara("user.tel", getPara("tel")), getPara("verifyCode"))){
                    renderJson(ResultFactory.createResult(Constant.ResultCode.VERIFY_CODE_ERR, null));
                    return;
                }
                data.add(new BasicNameValuePair("token", Md5Crypt.apr1Crypt(getPara("user.tel", getPara("tel")), PropKit.get("authority.md5;key"))));
            }
            Map<String, String[]> paraMap = getParaMap();
            for (String key : paraMap.keySet()) {
                if (!key.equals("verifyCode")) {
                    data.add(new BasicNameValuePair(key, paraMap.get(key)[0]));
                }
            }
            try {
                renderJson(HttpUtil.postJson(PropKit.get("remote.server.biz"), data, getHeader("tokenId"), "application/x-www-form-urlencoded"));
            } catch (IOException e) {
                e.printStackTrace();
                renderJson(ResultFactory.error("中转程序请求服务异常["+ e.getMessage() +"]"));
            }
        }
    }

    @Before(GET.class)
    public void getImgbase() {
        String url = PropKit.get("remote.server.img") + "?imgurl=" + getPara("imgurl");
        try {
            renderJson(HttpUtil.getJson(url, null));
        } catch (IOException e) {
            e.printStackTrace();
            renderJson(ResultFactory.error("中转程序请求服务异常["+ e.getMessage() +"]"));
        }
    }
}
