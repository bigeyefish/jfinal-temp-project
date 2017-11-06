package com.wsy.util;

import com.jfinal.kit.PropKit;
import com.wsy.model.Interviewer;

import static org.junit.Assert.*;

/**
 * Created by sanyihwang on 2017/11/6.
 */
public class CardUtilTest {

    private String jsonStr = "{\"auth\":0,\"codeId\":\"367a7615323d4564\",\"idNum\":\"842e260179e7b2203026d85cca94a59220fc9ca45afd1dce6da663e057bf2d887ba758e4c452a8b9562e47c9ffec7d7b\",\"name\":\"1c4ec211686e4f8fb4ebf7e681985fb4bc3745526cfbb295b7b71f62fdac33c6\",\"qrType\":1,\"telephone\":\"50f384d8f2a6d977f4834098e4d0a7103c0434f05c25365c4c8c7c8b924dcca9\",\"url\":\"https://app.mycards.net.cn:8443/mycards/info/getInfo\"}";

    static {
        PropKit.use("config.properties");
    }

    @org.junit.Test
    public void decodeCardInfo() throws Exception {
        Interviewer interviewer = CardUtil.decodeCardInfo(jsonStr);
        System.out.println(interviewer);
    }

}