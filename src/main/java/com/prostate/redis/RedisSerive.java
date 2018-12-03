package com.prostate.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author MaxCoder
 * @date 2017.04.09
 * <p>
 * Redis 服务
 */
@Service
public class RedisSerive {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 添加一条缓存数据
     * @param key
     * @param val
     * @param l 过期时间 秒
     */
    public void insert(String key, String val, long l) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, val, l, TimeUnit.SECONDS);
    }

}
