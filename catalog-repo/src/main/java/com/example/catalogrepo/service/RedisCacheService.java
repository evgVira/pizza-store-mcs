package com.example.catalogrepo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisCacheService {

    private final RedisTemplate<Object, Object> redisTemplate;

    private static final int CACHE_THRESHOLD = 3;

    public boolean needCacheData(UUID id, String cacheKey){
        Long requestCount =  redisTemplate.opsForValue().increment(cacheKey);
        if(requestCount == 1){
            redisTemplate.expire(cacheKey, 10, TimeUnit.MINUTES);
        }
        return requestCount < CACHE_THRESHOLD ? false : true;
    }
}
