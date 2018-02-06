package com.wsy.util;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.StrKit;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Jony on 2017/3/24.
 */
public class HttpUtil {

    public static String postJson(String url, JSONObject jsonObj, String contentType) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5 * 60 * 1000).setConnectTimeout(5 * 60 * 1000).build();//设置请求和传输超时时间
        post.setConfig(requestConfig);
        StringEntity entity = new StringEntity(jsonObj.toString(), "UTF-8");
        if (StrKit.notBlank(contentType)) {
            post.setHeader("Content-Type", contentType);
            entity.setContentType(contentType);
        }
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
