package com.xth.config;

import lombok.Data;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/27 0027 下午 22:52
 */
@Data
public class SpringJedisPoolConfig {

    private Integer maxActive;

    private Integer maxWait;

    private Integer maxIdle;

    private Integer minIdle;
}

