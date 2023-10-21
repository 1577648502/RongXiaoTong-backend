package com.lfg.rongxiaotong.utius;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Component
public class RedisDelKeys {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public Set<String> redisDelKeys(String redisName, HttpServletRequest request) {
        Set<String> keys = redisTemplate.keys(redisName + request.getSession().getId() + "*");
        System.out.println(keys);
        if (keys != null) {
            redisTemplate.delete(keys);
        }
        return keys;
    }
}