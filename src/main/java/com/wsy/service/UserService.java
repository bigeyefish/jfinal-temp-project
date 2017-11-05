package com.wsy.service;

import com.jfinal.aop.Clear;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.plugin.activerecord.Db;
import com.wsy.model.User;
import com.wsy.util.LogUtil;

import java.util.List;

/**
 * Created by Lenovo on 2017/10/15.
 */
public class UserService {

    /**
     * 根据用户名获取用户信息
     * @param userName 用户名
     * @return
     */
    @Clear(GET.class)
    public User getUserByName(String userName) {
        try {
            return User.dao.findFirst(Db.getSqlPara("index.findUserByName", userName));
        } catch (Exception e) {
            LogUtil.LogType.errorLog.error(e.getMessage());
            return null;
        }
    }

    /**
     * 根据家庭编号查询家庭成员
     * @param familyId
     * @return
     */
    public List<User> findUsersByFamily(int familyId) {
        return User.dao.find(Db.getSqlPara("index.findUsersByFamily", familyId));
    }
}
