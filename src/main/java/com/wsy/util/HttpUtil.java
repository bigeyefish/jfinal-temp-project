package com.wsy.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Jony on 2017/3/24.
 */
public class HttpUtil {

    public static String postJson(String url, JSONObject jsonObj, String tokenId) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setConfig(RequestConfig.custom().setSocketTimeout(5 * 60 * 1000).setConnectTimeout(5 * 60 * 1000).build());
        StringEntity entity = new StringEntity(jsonObj.toString(), "UTF-8");
        post.setHeader("tokenId", tokenId);
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
        String result = null;
        if (response != null) {
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                LogUtil.LogType.errorLog.error("http request faild: [" + url + "][" + code + "][" + jsonObj.toJSONString() + "]");
            }
        }
        return result;
    }

    public static String getJson(String url, String tokenId) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        get.setHeader("tokenId", tokenId);
        get.setConfig(RequestConfig.custom().setSocketTimeout(5 * 60 * 1000).setConnectTimeout(5 * 60 * 1000).build());
        get.setHeader("Content-Type", "application/json;charset=UTF-8");
        HttpResponse response = httpClient.execute(get);
        String result = null;
        if (response != null) {
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                LogUtil.LogType.errorLog.error("http request faild: [" + url + "][" + code + "]");
            }
        }
        return result;
    }
}
