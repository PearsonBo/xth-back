package com.xth.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hu Jianbo
 * @date: 2018/7/26
 */
@RestController
@RequestMapping("/risk/rest/ruleGroup")
public class TestController {

    @RequestMapping("/create")
    public String yes() {
        return "yes";
    }

    @RequestMapping("/no")
    public String no() {
        return "no";
    }
}
