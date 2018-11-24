package com.xth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/19 0019 下午 21:53
 */
@Data
@Configuration
@ConfigurationProperties("wechat")
public class WeChatConfig {

    private String appId;
    private String appSecret;
    private String loginurl;
    private String encryptKey;

}
