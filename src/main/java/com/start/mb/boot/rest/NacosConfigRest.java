package com.start.mb.boot.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wll
 * @version version
 * @title NacosConfigRest
 * @desc
 * @date 2020/3/30
 */
@RestController
@RefreshScope
public class NacosConfigRest {

    @Value("${user.name:null}")
    String userName;

    @Value("${user.age:25}")
    int age;

    @Value("${current.env:null}")
    String current;

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @GetMapping("/user")
    public String simple() {

        System.out.println("useLocalCache:" + useLocalCache);
        return "Hello Nacos Config!" + "Hello " + userName + " " + age + "!" + "current:" + current;
    }

}
