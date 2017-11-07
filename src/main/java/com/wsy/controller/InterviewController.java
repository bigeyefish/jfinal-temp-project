package com.wsy.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.wsy.service.InterviewService;


/**
 * 访客
 * Created by sanyihwang on 2017/11/6.
 */
public class InterviewController extends Controller {

    private InterviewService interviewService = null;

    public InterviewController() {
        interviewService = new InterviewService();
    }

    /**
     * 访客登记
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void checkIn() {
        renderJson(interviewService.checkIn(getPara("data")));
    }

    /**
     * 查询访客记录
     */
    public void queryInterviewerById() {
        renderJson(interviewService.queryInterviewerById(getPara("certId"), getParaToInt("page", 1), getParaToInt("size", 10)));
    }
}
