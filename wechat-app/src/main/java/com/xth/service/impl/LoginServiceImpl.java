package com.xth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import com.xth.config.WeChatConfig;
import com.xth.dao.client.ClientDao;
import com.xth.model.LoginResponseVo;
import com.xth.model.bo.client.Client;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.model.so.client.ClientSo;
import com.xth.service.LoginService;
import com.xth.service.RedisReadHelper;
import com.xth.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/19 0019 下午 22:15
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private RedisReadHelper redisReadHelper;

    @Autowired
    private ClientDao clientDao;

    private static Cache<String, Object> loginCache = CacheBuilder.newBuilder()
            .expireAfterWrite(24, TimeUnit.HOURS)
            .build();

    @Override
    public String login(String code) {
        Map paramMap = Maps.newHashMap();
        paramMap.put("grant_type", "authorization_code");
        paramMap.put("appid", weChatConfig.getAppId());
        paramMap.put("secret", weChatConfig.getAppSecret());
        paramMap.put("js_code", code);

        String url = weChatConfig.getLoginurl();

        String result = HttpUtils.doGet(url, paramMap);
        log.info("登陆认证result:" + result);

        LoginResponseVo loginResponse = JSONObject.parseObject(result, LoginResponseVo.class);
        String openId = loginResponse.getOpenId();
        String sessionKey = loginResponse.getSessionKey();
        if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(sessionKey)) {
            log.error("登陆认证失败，result:" + result);
            throw new BizException("登陆认证失败", ExceptionType.LOGIN_ERR);
        }
        ClientSo clientSo = new ClientSo();
        clientSo.setOpenId(openId);
        List<Client> clients = clientDao.listBoBySo(clientSo);

        Client client = null;
        Long clientId = null;

        if (CollectionUtils.isEmpty(clients)) {
            log.info("用户首次登陆");
            client = new Client();
            client.setOpenId(openId);
            client.setLastLoginTime(new Date());
            client.setLoginNum(1L);
            client.setLockVersion(0);
            clientId = clientDao.insert(client);
        } else {
            log.info("用户不是首次登陆");
            client = clients.get(0);
            clientId = client.getId();
            client.setLastLoginTime(new Date());
            client.setLoginNum(client.getLoginNum() + 1);
            clientDao.update(client);
        }
        log.info("加密后的sessionId存入redis");
        String sessionId = null;
        try {
            sessionId = sessionKey;
            log.info("加密后的sessionId存入redis:" + sessionKey);
            redisReadHelper.saveStringInRedis(sessionKey, clientId.toString(), 6, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sessionId;
    }
}
