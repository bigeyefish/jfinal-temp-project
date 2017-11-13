package com.wsy.service;

import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.wsy.model.User;
import com.wsy.model.biz.Result;
import com.wsy.util.Constant;
import com.wsy.util.EncryptUtil;
import com.wsy.util.ResultFactory;
import com.wsy.util.TokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by Lenovo on 2017/10/15.
 */
public class UserService {

    public static final Logger log = LogManager.getLogger(UserService.class);

    /**
     * 使用token登陆
     * @param token
     * @return
     */
    public Result logIn(String token) {
        if (StrKit.isBlank(token)) {
            return ResultFactory.createResult(Constant.ResultCode.LEAK_PARAM);
        }
        String[] arr = TokenUtil.decodeToken(token);
        if (null != arr) {
            User user = User.dao.findById(Integer.parseInt(arr[0]));
            return loginResult(user);
        }
        return ResultFactory.createResult(Constant.ResultCode.LOGIN_FAIL);
    }

    /**
     * 登陆
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    public Result logIn(String userName, String password) {
        if (StrKit.isBlank(userName) || StrKit.isBlank(password)) {
            return ResultFactory.createResult(Constant.ResultCode.LEAK_PARAM);
        }
        String passMD5Str = EncryptUtil.getMD5(Constant.MD5_FRE + password.trim());
        if (null == passMD5Str) {
            return ResultFactory.createResult(Constant.ResultCode.MD5_ERR);
        }
        User user = User.dao.findFirst("select * from user t where t.user_name = ? and t.password = ?", userName, passMD5Str);
        return loginResult(user);
    }

    private Result loginResult(User user) {
        if (null != user) {
            log.warn("用户[{}]登录成功", user.getUserName());
            user.setLastLogin(new Date()).update();
            Kv kv = Kv.by("id", user.getId()).set("userName", user.getUserName()).set("nickName", user.getNickName());
            return ResultFactory.success(kv);
        }
        log.warn("用户登录失败");
        return ResultFactory.createResult(Constant.ResultCode.LOGIN_FAIL);
    }

    /**
     * 修改密码
     * @param userId 用户id
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @return
     */
    public Result modifyPassword(int userId, String oldPass, String newPass) {
        if (null == oldPass || null == newPass) {
            return ResultFactory.createResult(Constant.ResultCode.LEAK_PARAM);
        }
        if (newPass.trim().length() < 6 || newPass.trim().length() > 16) {
            return ResultFactory.createResult(Constant.ResultCode.PASSWD_LENGTH_ERR);
        }

        String oldPassMD5Str = EncryptUtil.getMD5(Constant.MD5_FRE + oldPass.trim());
        if (null == oldPassMD5Str) {
            return ResultFactory.createResult(Constant.ResultCode.MD5_ERR);
        }

        User user = User.dao.findFirst("select * from user t where t.id = ? and t.password = ?", userId, oldPassMD5Str);
        if (null == user) {
            return ResultFactory.createResult(Constant.ResultCode.PASSWD_INCORRECT);
        }

        String newPassMD5Str = EncryptUtil.getMD5(Constant.MD5_FRE + newPass.trim());
        if (null == newPassMD5Str) {
            return ResultFactory.createResult(Constant.ResultCode.MD5_ERR);
        }

        user.setPassword(newPassMD5Str).update();
        return ResultFactory.success(null);
    }

}
