package com.wsy.util;

import com.jfinal.kit.StrKit;
import com.wsy.model.User;
import com.wsy.model.biz.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by Lenovo on 2017/11/12.
 */
public class TokenUtil {

    public static final Logger log = LogManager.getLogger(TokenUtil.class);

    private static final String TOKEN_SEP_SIGN = "-";

    /**
     * 生成token字符串
     * @param id
     * @param userName
     * @return
     */
    public static String generateToken(int id, String userName) {
        String token =  EncryptUtil.encrypt(id + TOKEN_SEP_SIGN + userName + TOKEN_SEP_SIGN + new Date().getTime());
        User.dao.findById(id).setToken(token).update();
        return token;
    }

    /**
     * 删除用户token
     * @param id
     */
    public static void delUserToken(int id) {
        User.dao.findById(id).setToken("").update();
    }

    /**
     * 从token中解析出数据
     * @param token
     * @return
     */
    public static String[]  decodeToken(String token) {
        String key = EncryptUtil.decrypt(token);
        return key == null ? null : key.split(TOKEN_SEP_SIGN);
    }

    /**
     * 校验token
     * @param token
     * @return Result
     */
    public static Result validate(String token) {
        if (StrKit.isBlank(token)) {
            return ResultFactory.createResult(Constant.ResultCode.FIRST_LOGIN);
        }
        String[] arr = decodeToken(token);
        if (arr == null || arr.length == 0) {
            return ResultFactory.createResult(Constant.ResultCode.ILLEGAL_TOKEN);
        }
        User user = User.dao.findById(arr[0]);
        if (user == null) {
            return ResultFactory.createResult(Constant.ResultCode.USER_DONOT_EXIST);
        }
        if (!user.getIsactive()) {
            return ResultFactory.createResult(Constant.ResultCode.USER_INACTIVE);
        }
        if (!token.equals(user.getToken())) {
            return ResultFactory.createResult(Constant.ResultCode.TOKEN_TIMEOUT);
        }
        return ResultFactory.success(user);
    }
}
