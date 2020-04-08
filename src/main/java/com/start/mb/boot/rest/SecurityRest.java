package com.start.mb.boot.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wll
 * @desc SecurityRest
 * @link
 * @date 2020/4/8 2:42 下午
 */
@RestController
public class SecurityRest {

    @GetMapping("hello")
    public String hello() {
        return "hello spring security";
    }

}
