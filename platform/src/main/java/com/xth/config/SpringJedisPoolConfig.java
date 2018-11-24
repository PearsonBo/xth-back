package com.xth.config;

import lombok.Data;

/**
 * @author
 * @date 2018/2/24
 */
@Data
public class SpringJedisPoolConfig {

    private Integer maxActive;

    private Integer maxWait;

    private Integer maxIdle;

    private Integer minIdle;
}
