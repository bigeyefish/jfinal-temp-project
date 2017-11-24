package com.wsy.service;

import com.jfinal.plugin.activerecord.Db;
import com.wsy.model.Family;
import com.wsy.model.User;
import com.wsy.util.Constant;

import java.util.List;

/**
 * Created by Lenovo on 2017/11/22.
 */
public class FamilyService {

    public Family getFamilyById(int id) {
        return Family.dao.findFirstByCache(Constant.CACHE_KEY.USER_BASIC, id, "select * from family where id = ?", id);
    }

    /**
     * 根据家庭编号查询家庭成员
     * @param familyId
     * @return
     */
    public List<User> getUsersByFamily(int familyId) {
        return User.dao.find(Db.getSqlPara("index.findUsersByFamily", familyId));
    }
}
