package com.wsy.controller;

import com.jfinal.core.Controller;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class IndexController extends Controller {

    public void index() {
        render("/index.html");
    }
}
