package com.wsy.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.wsy.model.Interviewer;
import com.wsy.model.User;
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
     * 电子卡包访客登记
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void checkInCard() {
        renderJson(interviewService.checkInCard(getPara("data"), ((User)getSessionAttr("user")).getId()));
    }

    /**
     * NFC手机访客登记
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void checkInNFC() {
        renderJson(interviewService.checkInNFC(getBean(Interviewer.class), getPara("imgBase64"), getPara("cardId"), ((User)getSessionAttr("user")).getId()));
    }

    /**
     * 手动添加
     */
    @Clear(GET.class)
    @Before(POST.class)
    public void checkInManual() {
        renderJson(interviewService.checkInManual(getBean(Interviewer.class), ((User)getSessionAttr("user")).getId()));
    }

    /**
     * 查询访客记录
     */
    public void queryInterviewerById() {
        renderJson(interviewService.queryInterviewerById(((User)getSessionAttr("user")).getDistrictId(), getParaToInt("page", 1), getParaToInt("size", 10)));
    }
}
