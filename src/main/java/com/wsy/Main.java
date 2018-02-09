package com.wsy;

import com.jfinal.core.JFinal;

/**
 * Created by Lenovo on 2017/10/13.
 */
public class Main {

    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 80, "/Falcon");
    }
}
