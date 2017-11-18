package com.wsy.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.wsy.model.Resource;
import com.wsy.service.privilege.ResourceService;
import com.wsy.util.ResultFactory;

/**
 * Created by Lenovo on 2017/11/17.
 */
public class ResourceController extends Controller {

    private ResourceService resourceService;

    public ResourceController() {
        resourceService = new ResourceService();
    }

    public void getAllMenu() {
        renderJson(ResultFactory.success(resourceService.getAllMenu()));
    }

    @Clear(GET.class)
    @Before(POST.class)
    public void save() {
        Resource resource = getModel(Resource.class, "", true);
        resource.setCreatorId(getSessionAttr("userId")).setUpdatorId(getSessionAttr("userId"));
        renderJson(resourceService.add(resource));
    }
}
