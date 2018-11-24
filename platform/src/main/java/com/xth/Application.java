package com.xth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Hu Jianbo
 * @date: 2018/9/9
 */
@SpringBootApplication(scanBasePackages = {
        "com.xth.dao",
        "com.xth.controller",
        "com.xth.service",
        "com.xth.util",
        "com.xth.config",
        "com.xth.helper",
        "com.xth.filter"
}, exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@EnableWebMvc
@MapperScan(basePackages = {"com.xth.mapper"})
@ServletComponentScan(basePackages = {"com.xth"})
@EnableHystrix
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

