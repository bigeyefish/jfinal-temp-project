package com.wsy.service;

import com.jfinal.kit.StrKit;
import com.wsy.model.Interviewer;
import com.wsy.model.biz.Result;
import com.wsy.service.dataReport.PublicSecurityDataReporter;
import com.wsy.util.Constant;
import com.wsy.util.FalconsUtil;
import com.wsy.util.LogUtil;
import com.wsy.util.ResultFactory;

import java.util.Date;

/**
 * Created by sanyihwang on 2017/11/6.
 */
public class InterviewService {

    /**
     * 电子卡包登记访客
     * @param interviewer
     * @param cardId
     * @param userId
     * @return
     */
    public Result checkInCard(Interviewer interviewer, String cardId, int userId) {
        if (null == interviewer || null == cardId) {
            return ResultFactory.createResult(Constant.ResultCode.LEAK_PARAM, null);
        }
        interviewer.setIdNumCode(cardId);
        interviewer.setCreateTime(new Date());
        interviewer.setCreateBy(userId);
        try {
            // 存储数据库
            interviewer.save();

            // idNumCode推送
            new Thread(new PublicSecurityDataReporter(interviewer)).start();

            return ResultFactory.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.error(e.getMessage());
        }
    }

    /**
     * NFC手机登记访客
     * @param interviewer
     * @param imgBase64
     * @param cardId
     * @return
     */
    public Result checkInNFC(Interviewer interviewer, String imgBase64, String cardId, int userId) {
        if (null == interviewer || null == imgBase64) {
            return ResultFactory.createResult(Constant.ResultCode.LEAK_PARAM, null);
        }
        interviewer.setIdNumCode(cardId);
        interviewer.setCreateTime(new Date());
        interviewer.setCreateBy(userId);
        interviewer.setPortrait(imgBase64);
        try {
            // 保存头像
            //String path = PropKit.get("path.portrait") + "/" + UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            //OutputStream out = new FileOutputStream(PathKit.getWebRootPath() + path);
            //out.write(Base64Kit.decode(imgBase64));
            //out.flush();
            //out.close();

            // 存储数据库
            //interviewer.setPortrait(path);
            interviewer.save();
            // 调用猎鹰平台
            Result result = FalconsUtil.reportData(interviewer, imgBase64);

            // idNumCode推送
            new Thread(new PublicSecurityDataReporter(interviewer)).start();

            if (result.getCode() == 0) {
                return ResultFactory.success(null);
            }
            result.setData("调用猎鹰平台异常");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.LogType.errorLog.error("checkInNFC Err:" + e.getMessage());
            return ResultFactory.error(e.getMessage());
        }
    }

    /**
     * 手动登记访客
     * @param interviewer
     * @param userId
     * @return
     */
    public Result checkInManual(Interviewer interviewer, int userId) {
        if (null == interviewer || StrKit.isBlank(interviewer.getName()) || StrKit.isBlank(interviewer.getIdNum())) {
            return ResultFactory.createResult(Constant.ResultCode.LEAK_PARAM, null);
        }
        interviewer.setCreateTime(new Date());
        interviewer.setCreateBy(userId);
        try {
            interviewer.save();
            // idNumCode推送
            new Thread(new PublicSecurityDataReporter(interviewer)).start();
            return ResultFactory.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.LogType.errorLog.error("checkInManual Err:" + e.getMessage());
            return ResultFactory.error(e.getMessage());
        }
    }

    /**
     * 分页查询访客记录
     * @param districtId 登录用户
     * @param page 页码
     * @param size 每页数量
     * @return
     */
    public Result queryInterviewerById(int districtId, int page, int size) {
        try {
            return ResultFactory.success(Interviewer.dao.paginate(page, size, "select *", "from interviewer where create_by in (select id from user where district_id = ? ) order by create_time desc", districtId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.error(e.getMessage());
        }
    }
}
