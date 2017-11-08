package com.wsy.service;

import com.jfinal.aop.Clear;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.plugin.activerecord.Db;
import com.wsy.model.User;
import com.wsy.model.biz.Result;
import com.wsy.util.Constant;
import com.wsy.util.LogUtil;
import com.wsy.util.ResultFactory;

/**
 * Created by Lenovo on 2017/10/15.
 */
public class UserService {

    /**
     * 根据用户名获取用户信息
     * @param userName 用户名
     * @return
     */
    public User getUserByName(String userName) {
        try {
            return User.dao.findFirst(Db.getSqlPara("index.findUserByName", userName));
        } catch (Exception e) {
            LogUtil.LogType.errorLog.error(e.getMessage());
            return null;
        }
    }

    /**
     * 修改用户密码
     * @param userId
     * @param oldPass
     * @param newPass
     * @return
     */
    public Result modifyPassword(int userId, String oldPass, String newPass) {
        if (null == oldPass || null == newPass) {
            return ResultFactory.createResult(Constant.ResultCode.LEAK_PARAM, null);
        }
        try {
            User user = User.dao.findFirst("select * from user t where t.id = ? and password = ? ", userId, oldPass);
            if (null == user) {
                return ResultFactory.createResult(Constant.ResultCode.PASSWORD_ERR, null);
            }
            if (newPass.trim().length() < 6 || newPass.trim().length() > 16) {
                return ResultFactory.createResult(Constant.ResultCode.PASSWORD_CHECK_ERR, null);
            }
            user.setPassword(newPass).update();
            return ResultFactory.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.error(e.getMessage());
        }
    }
}
