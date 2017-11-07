package com.wsy.util;

import com.jfinal.kit.Base64Kit;
import com.jfinal.kit.PropKit;
import com.wsy.model.Interviewer;
import com.wsy.model.biz.Result;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

//import com.wsy.model.Interviewer;

/**
 * Created by sanyihwang on 2017/11/6.
 */
public class FalconsUtilTest {

    static {
        PropKit.use("config.properties");
    }

    @Test
    public void reportData() throws Exception {

        String imgFile = "e://914339156625484421.jpg";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Base64编码过的字节数组字符串
        String image = Base64Kit.encode(data);

        Interviewer interviewer = new Interviewer();
        interviewer.setTel("13812345678");
        interviewer.setIdNum("420000198702154652");
        interviewer.setName("张三");

        Result result = FalconsUtil.reportData(interviewer, image);
        System.out.println(result);
    }
}