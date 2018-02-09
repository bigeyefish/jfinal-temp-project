package com.wsy.Handler;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class ResourceHandler extends Handler {
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        if (request.getMethod().equalsIgnoreCase("options")) {
            response.addHeader("Access-Control-Allow-Headers", "tokenId,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
        } else {
            next.handle(target, request, response, isHandled);
        }
    }
}
