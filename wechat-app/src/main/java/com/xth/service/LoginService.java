package com.xth.service;

import com.xth.model.LoginResponseVo;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/19 0019 下午 22:15
 */
public interface LoginService {

    /**
     * 微信登陆
     *
     * @param code 用户登录凭证
     * @return sessionId 加密后返回的sessionId
     */
    String login(String code);
}
