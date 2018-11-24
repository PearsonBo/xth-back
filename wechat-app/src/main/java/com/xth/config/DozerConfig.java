package com.xth.config;

import com.xth.util.DozerHelper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/29 0029 上午 2:03
 */
@Configuration
public class DozerConfig {

    @Bean
    public DozerHelper getDozerHelper() {
        DozerHelper dozerHelper = new DozerHelper();
        Mapper mapper = new DozerBeanMapper();
        dozerHelper.setMapper(mapper);
        return dozerHelper;
    }

}