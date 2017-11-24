package com.wsy.service.privilege;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.wsy.model.Resource;
import com.wsy.model.User;
import com.wsy.model.biz.Result;
import com.wsy.util.Constant;
import com.wsy.util.ResultFactory;

import java.util.*;

/**
 * 资源服务类（菜单、数据）
 * Created by Lenovo on 2017/11/14.
 */
public class ResourceService {

    /**
     * 获取用户资源数据
     *
     * @param userId 用户id
     * @return 资源数据
     */
    public Map getUserAllResource(int userId, boolean issuper) {
        return CacheKit.get(Constant.CACHE_KEY.USER_RESOURCE, userId, () -> {
            List<Resource> resourceList = null;
            if (issuper) {
                resourceList = Resource.dao.find("select * from resource order by seq");
            } else {
                String sql = "select * from resource where isactive=1 and (type=4 or id in (" +
                        "select resource_id from permission_resource where permission_id in (" +
                        "select permission_id from role_permission where role_id in (" +
                        "select role_id from user_role where user_id = ? )))) order by seq";
                resourceList = Resource.dao.find(sql, userId);
            }
            Map<String, List<Resource>> result = new HashMap<>();
            List<Resource> menuList = new ArrayList<>();
            for (Resource resource : resourceList) {
                result.putIfAbsent(resource.getType() + "", new ArrayList<>());
                if (resource.getType() == Constant.RESOURCE_TYPE.MENU) {
                    if (resource.getParentId() == 0) {
                        result.get(resource.getType() + "").add(resource);
                    } else {
                        menuList.add(resource);
                    }
                } else {
                    result.get(resource.getType() + "").add(resource);
                }
            }
            for (Resource resource : result.get(Constant.RESOURCE_TYPE.MENU + "")) {
                fillChildren(resource, menuList);
            }
            return result;
        });
    }

    /**
     * 获取整个目录树
     *
     * @return 目录树
     */
    public List<Resource> getAllMenu() {
        return CacheKit.get(Constant.CACHE_KEY.USER_RESOURCE, "ALL_MENU", () -> {
            List<Resource> resourceList = Resource.dao.find("select * from resource where type = 1 order by seq");
            List<Resource> result = new ArrayList<Resource>();
            resourceList.stream().filter(resource -> resource.getParentId() == 0).forEach(resource -> {
                result.add(resource);
                fillChildren(resource, resourceList);
            });
            return result;
        });
    }

    /**
     * 根据类型分页查询除菜单外的资源数据
     *
     * @param type    类型 2-按钮 3-数据 4-通用
     * @param page    页码
     * @param size    大小
     * @param keyWord 过滤条件
     */
    public Page<Resource> getResourceByType(int type, int page, int size, String keyWord) {
        String extSql = "from resource where type = ? ";
        if (StrKit.notBlank(keyWord)) {
            extSql += " and (url like '%" + keyWord + "%' or name like '%" + keyWord + "%' or code like '%" + keyWord + "%')";
        }
        extSql += " order by url";
        return Resource.dao.paginate(page, size, "select *", extSql, type);
    }

    /**
     * 添加资源文件
     * @param resource
     * @return
     */
    public Result add(Resource resource) {
        if (resource.getType() == Constant.RESOURCE_TYPE.MENU) {
            // 检查同一级目录同名
            List<Resource> resources = Resource.dao.find("select * from resource where type = ? and name = ? and parent_id = ?", resource.getType(), resource.getName(), resource.getParentId());
            if (resources.size() > 0) {
                return ResultFactory.createResult(Constant.ResultCode.DUPLICATE_RESOURCE);
            }
            resource.setCode(Resource.dao.findByIdLoadColumns(resource.getParentId(), "code").getCode() + resource.getParentId() + "_");
        } else {
            List<Resource> resources = Resource.dao.find("select * from resource where type = ? and code = ? ", resource.getType(), resource.getCode());
            if (resources.size() > 0) {
                return ResultFactory.createResult(Constant.ResultCode.DUPLICATE_RESOURCE);
            }
        }
        resource.save();
        return ResultFactory.success(null);
    }

    /**
     * 删除资源
     * @param id
     */
    public void deleteResource(int id) {
        // 菜单要找出相关子菜单

    }

    /**
     * 填充子节点
     *
     * @param resource 菜单树
     * @param menuList 菜单列表
     */
    private Resource fillChildren(Resource resource, List<Resource> menuList) {
        if (!hasChild(resource, menuList)) {
            return resource;
        }
        for (Resource r : menuList) {
            if (Objects.equals(r.getParentId(), resource.getId())) {
                r.setLevel(resource.getLevel() + 1);
                if (hasChild(r, menuList)) {
                    r = fillChildren(r, menuList);
                }
                resource.getChildren().add(r);
            }
        }
        return resource;
    }

    /**
     * 判断是否有子节点
     *
     * @param resource 菜单
     * @param menuList 菜单列表
     * @return 菜单是否有子菜单
     */
    private boolean hasChild(Resource resource, List<Resource> menuList) {
        for (Resource r : menuList) {
            if (Objects.equals(r.getParentId(), resource.getId())) {
                return true;
            }
        }
        return false;
    }
}
