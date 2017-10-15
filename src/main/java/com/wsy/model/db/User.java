package com.wsy.model.db;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class User extends Model<User> {
    public static final User dao = new User().dao();
}
