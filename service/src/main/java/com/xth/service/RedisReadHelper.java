package com.xth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/10 0010 下午 23:39
 */
@Service
public class RedisReadHelper {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisObjectTemplate;

    public String getStringInRedis(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void saveStringInRedis(String key, String value) {
        if (key == null || value == null) {
            return;
        }
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void saveStringInRedis(String key, String value, long timeout, TimeUnit unit) {
        if (key == null || value == null) {
            return;
        }
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public void delete(String... keys) {
        if (keys == null || keys.length == 0) {
            return;
        }
        if (keys.length == 1) {
            redisObjectTemplate.delete(keys[0]);
        } else {
            redisObjectTemplate.delete(Arrays.asList(keys));
        }
    }
}
