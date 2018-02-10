package com.wsy.util;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.StrKit;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jony on 2017/3/24.
 */
public class HttpUtil {

    public static String postJson(String url, List<NameValuePair> data, String tokenId, String contentType) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setConfig(RequestConfig.custom().setSocketTimeout(5 * 60 * 1000).setConnectTimeout(5 * 60 * 1000).build());

        post.setHeader("tokenId", tokenId);
        if (StrKit.notBlank(contentType)) {
            post.setHeader("Content-Type", contentType);
        }
        post.setEntity(new UrlEncodedFormEntity(data,"utf-8"));
        HttpResponse response = httpClient.execute(post);
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

    public static String getJson(String url, String tokenId) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        get.setHeader("tokenId", tokenId);
        get.setConfig(RequestConfig.custom().setSocketTimeout(5 * 60 * 1000).setConnectTimeout(5 * 60 * 1000).build());
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

    public static String postMsgJson(String url, JSONObject jsonObj) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setConfig(RequestConfig.custom().setSocketTimeout(5 * 60 * 1000).setConnectTimeout(5 * 60 * 1000).build());
        StringEntity entity = new StringEntity(jsonObj.toString(), "UTF-8");
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
}
