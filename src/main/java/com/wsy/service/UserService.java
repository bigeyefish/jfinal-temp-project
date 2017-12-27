package com.wsy.service;

import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.wsy.model.ScoreFlow;
import com.wsy.model.User;
import com.wsy.model.biz.Result;
import com.wsy.service.privilege.ResourceService;
import com.wsy.util.Constant;
import com.wsy.util.EncryptUtil;
import com.wsy.util.ResultFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户相关服务
 * Created by Lenovo on 2017/10/15.
 */
public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    private ResourceService resourceService = null;
    private FamilyService familyService = null;

    public UserService() {
        resourceService = new ResourceService();
        familyService = new FamilyService();
    }

    /**
     * 登陆
     * @param userName 用户名
     * @param password 密码
     * @return Result
     */
    public Result logIn(String userName, String password) {
        if (StrKit.isBlank(userName) || StrKit.isBlank(password)) {
            return ResultFactory.createResult(Constant.ResultCode.LEAK_PARAM);
        }
        String passMD5Str = EncryptUtil.getMD5(Constant.MD5_FRE + password.trim());
        if (null == passMD5Str) {
            return ResultFactory.createResult(Constant.ResultCode.MD5_ERR);
        }
        User user = User.dao.findFirst("select * from user t where t.user_name = ? and t.password = ? and t.isactive = 1", userName, passMD5Str);
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
     * 获取用户的资源信息
     * @param userId 用户Id
     * @return Result
     */
    public Result getUserInfo(int userId) {
        User user = getUserBasic(userId);
        if (null == user) {
            return ResultFactory.createResult(Constant.ResultCode.USER_DONOT_EXIST);
        }
        Kv kv = Kv.by("user", user).set("resource", resourceService.getUserAllResource(userId, user.getIssuper()))
                .set("familyUser", familyService.getUsersByFamily(user.getFamilyId()));
        return ResultFactory.success(kv);
    }

    /**
     * 修改密码
     * @param userId 用户id
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @return Result
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

    /**
     * 从缓存中获取用户基础信息（不包含密码）
     * @param userId 用户Id
     * @return 基础用户信息
     */
    public static User getUserBasic(int userId) {
        return User.dao.findFirstByCache(Constant.CACHE_KEY.USER_BASIC, userId, "select id,user_name,nick_name,issuper,avatar,sex,mobile,family_id from user where id = ?", userId);
    }

    /**
     * 从缓存中获取带敏感信息（token、密码）的用户基础信息
     * @param userId 用户Id
     * @return 带敏感数据的用户信息
     */
    public static User getUserBasicSecurity(int userId) {
        return User.dao.findFirst("select * from user where id = ?", userId);
    }

    /**
     * 增加或者扣减分值
     * @param userId 用户Id
     * @param score 分值
     * @param jobId jobId
     */
    Result addScore(int userId, double score, int jobId) {
        User user = User.dao.findById(userId);
        double beforeScore = user.getScore();
        if (user.getScore() + score < 0) {
            return ResultFactory.error(Constant.ResultCode.SCORE_NOT_ENOUGH);
        }

        user.setScore(beforeScore + score).update();

        ScoreFlow scoreFlow = new ScoreFlow();
        scoreFlow.setScore(score);
        scoreFlow.setCreatedTime(new Date());
        scoreFlow.setUserId(userId);
        scoreFlow.setJobId(jobId);
        scoreFlow.setBeforeScore(beforeScore);
        scoreFlow.setAfterScore(beforeScore + score);
        scoreFlow.save();

        return ResultFactory.success(beforeScore + score);
    }

    /**
     * 根据条件查询分值记录
     * @param queryParam 查询参数
     * @param page 页码
     * @param size 数量
     * @return Result
     */
    public Result queryScoreRecord(Map<String, String[]> queryParam, int page, int size) {
        StringBuilder sql = new StringBuilder("from score_flow t left join job t1 on t.job_id = t1.id left join task t2" +
                " on t1.task_id = t2.id where 1 = 1");
        List<Object> param = new ArrayList<>();
        if (queryParam.get("taskId") != null) {
            sql.append(" and t2.id = ?");
            param.add(queryParam.get("taskId")[0]);
        }
        if (queryParam.get("userId") != null) {
            sql.append(" and t.user_id = ?");
            param.add(queryParam.get("userId")[0]);
        }
        if (queryParam.get("date") != null) {
            sql.append(" and t.DATE(`created_time`) = str_to_date(?, '%Y-%m-%d')");
            param.add(queryParam.get("date")[0]);
        }
        sql.append(" order by t.created_time desc");
        Page<Record> result = Db.paginate(page, size, "select t.*, t2.name", sql.toString(), param.toArray());

        return ResultFactory.success(result);
    }
}
