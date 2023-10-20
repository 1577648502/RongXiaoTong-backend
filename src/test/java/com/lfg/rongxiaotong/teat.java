package com.lfg.rongxiaotong;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class teat {
    @Resource
    private RedisTemplate <String,Object> redisTemplate;
    @Test
    void test(){
        redisTemplate.opsForValue().set("name","xiaoming",60, TimeUnit.MINUTES);
    }
}
