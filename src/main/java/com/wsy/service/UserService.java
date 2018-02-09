package com.wsy.service;

import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.wsy.model.User;
import com.wsy.model.biz.Result;
import com.wsy.util.Constant;
import com.wsy.util.LogUtil;
import com.wsy.util.ResultFactory;
import org.apache.commons.codec.digest.Md5Crypt;

/**
 * Created by Lenovo on 2017/10/15.
 */
public class UserService {

    private boolean save;

    /**
     * 根据用户名(电话号码)获取用户信息
     * @param tel 电话号码
     * @return
     */
    public User getUserByTel(String tel) {
        try {
            return User.dao.findFirst(Db.getSqlPara("index.findUserByTel", tel));
        } catch (Exception e) {
            LogUtil.LogType.errorLog.error(e.getMessage());
            return null;
        }
    }

    /**
     * 添加系统用户
     * @param user
     * @param token 验证码
     * @return
     */
    public Result addUser(User user, String token) {
        // 校验字段
        if (StrKit.isBlank(user.getUserName()) || StrKit.isBlank(user.getPassword()) ||
                StrKit.isBlank(user.getIdNum()) || StrKit.isBlank(user.getTel()) || StrKit.isBlank(token)) {
            return ResultFactory.createResult(Constant.ResultCode.LEAK_PARAM, null);
        }

        // 校验加密串
        if (!Md5Crypt.apr1Crypt(user.getTel(), PropKit.get("authority.md5;key")).equals(token)) {
            return ResultFactory.createResult(Constant.ResultCode.SECUR_VALID_FAILD, null);
        }

        // 检查重复电话号码
        User user1 = getUserByTel(user.getTel());
        if (null != user1) {
            return ResultFactory.createResult(Constant.ResultCode.TEL_EXIST, null);
        }

        try {
            user.save();
            return ResultFactory.success(user.getId());
        } catch (Exception e) {
            LogUtil.LogType.errorLog.error("regist user error: {}", e.getMessage());
            e.printStackTrace();
            return ResultFactory.error(e.getMessage());
        }
    }

    /**
     * 重置用户密码
     * @param tel 手机号码
     * @param newPass 新密码
     * @param token md5加密串
     * @return 修改结果
     */
    public Result resetPassword(String tel, String newPass, String token) {
        if (null == tel || null == newPass || null == token) {
            return ResultFactory.createResult(Constant.ResultCode.LEAK_PARAM, null);
        }
        try {
            // 校验加密串
            if (!Md5Crypt.apr1Crypt(tel, PropKit.get("authority.md5;key")).equals(token)) {
                return ResultFactory.createResult(Constant.ResultCode.SECUR_VALID_FAILD, null);
            }

            User user = User.dao.findFirst("select * from user t where t.tel = ? ", tel);
            if (null == user) {
                return ResultFactory.createResult(Constant.ResultCode.TEL_NOT_EXIST, null);
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
