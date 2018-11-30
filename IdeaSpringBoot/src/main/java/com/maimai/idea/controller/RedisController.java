package com.maimai.idea.controller;

import com.maimai.idea.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 */
@RestController
public class RedisController {

    @Autowired
    private ApplicationArguments applicationArguments;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "mai")
    public String getMai() {
        return redisUtil.get("maimai").toString();
    }

    @RequestMapping(value = "setmai")
    public boolean getMai(String v) {

        return redisUtil.set("maimai", v);

    }

    @RequestMapping(value = "app")
    public String getApp(String v) {
        //获取所有 不包含--server.port
        System.out.println(applicationArguments.getNonOptionArgs());
        return "app";

    }




}
