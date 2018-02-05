package com.wsy.service.dataReport;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.wsy.model.Interviewer;
import com.wsy.util.Constant;
import com.wsy.util.HttpUtil;
import com.wsy.util.LogUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * 数据上报公安
 * Created by Lenovo on 2018/2/3.
 */
public class PublicSecurityDataReporter implements Runnable, IDataReporter {
    private static final Logger log = LogManager.getLogger(PublicSecurityDataReporter.class);

    private Interviewer interviewer;

    public PublicSecurityDataReporter(Interviewer interviewer) {
        this.interviewer = interviewer;
    }

    @Override
    public void report() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CardID", StrKit.isBlank(interviewer.getIdNumCode()) ? "102abad676fc42e1" : interviewer.getIdNumCode());
        jsonObject.put("Build", StrKit.isBlank(interviewer.getBuildingUnit()) ? "05栋1单元" : interviewer.getBuildingUnit());
        jsonObject.put("Community", StrKit.isBlank(interviewer.getRoom()) ? "光谷理想城" : interviewer.getRoom());
        jsonObject.put("Name", interviewer.getName());
        jsonObject.put("Phone", interviewer.getTel());
        jsonObject.put("PersonID", interviewer.getIdNum());

        try {
            String result = HttpUtil.postJson(PropKit.get("card.idNumCode.push.server"), jsonObject, "application/json");
            JSONObject retJson = JSONObject.parseObject(result);
            if (retJson.getInteger("code") == Constant.ResultCode.SUCCESS) {
                log.info("report data {} to public security success. {}", jsonObject.toJSONString(), result);
            } else {
                LogUtil.LogType.errorLog.error("report data {} to public security failed: {}", jsonObject.toJSONString(), result);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.LogType.errorLog.error("report data {} to public security error: {}", jsonObject.toString(), e.getMessage());
        }
    }

    @Override
    public void run() {
        report();
    }
}
