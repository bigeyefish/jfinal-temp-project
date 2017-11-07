package com.wsy.service;

import com.wsy.model.biz.Result;
import com.wsy.util.CardUtil;
import com.wsy.util.Constant;
import com.wsy.util.FalconsUtil;
import com.wsy.util.ResultFactory;

/**
 * Created by sanyihwang on 2017/11/6.
 */
public class InterviewService {

    /**
     * 登记访客
     * @param jsonStr
     * @return
     */
    public Result checkIn(String jsonStr) {
        // 解析电子卡包数据
        Interviewer interviewer = CardUtil.decodeCardInfo(jsonStr);
        if (null == interviewer) {
            return ResultFactory.createResult(Constant.ResultCode.DECODE_CARD_ERR, null);
        }
        // 存储数据库
        interviewer.save();
        // 调用猎鹰平台
        Result result = FalconsUtil.reportData(interviewer);
        if (result.getCode() == 0) {
            return ResultFactory.success(null);
        }
        result.setData("调用猎鹰平台异常");
        return result;
    }
}
