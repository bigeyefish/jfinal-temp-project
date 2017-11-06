package com.wsy.util;

import com.jfinal.kit.PropKit;
import com.wsy.model.Interviewer;

/**
 * Created by sanyihwang on 2017/11/6.
 */
public class CardUtilTest {

    private String jsonStr = "{\"auth\":0,\"codeId\":\"102abad676fc42e1\",\"idNum\":\"f701b4a895076300c16cc16bb0985c5cf80a2b789ac2b4261b2f62f24f60d3db71482c1a77d9f88415a751ff3c12a39c\",\"name\":\"41204b3e2b58058cd0dd1122b03b517e77bc846a1ddcaf976a09d47ab6340d00\",\"qrType\":1,\"telephone\":\"bfab5ce110930a4b9a6dd870bf86e6c24915e151038c55becd4bd748d9ebb49e\",\"url\":\"https://app.mycards.net.cn:8443/mycards/info/getInfo\"}";

    static {
        PropKit.use("config.properties");
    }

    @org.junit.Test
    public void decodeCardInfo() throws Exception {
        Interviewer interviewer = CardUtil.decodeCardInfo(jsonStr);
        System.out.println(interviewer);
    }

}