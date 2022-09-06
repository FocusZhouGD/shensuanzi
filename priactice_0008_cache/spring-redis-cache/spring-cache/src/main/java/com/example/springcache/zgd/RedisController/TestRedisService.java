package com.example.springcache.zgd.RedisController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.convert.StringToRedisClientInfoConverter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void addRedis() {
         //缓存中添加key以及过期时间  字符串

        stringRedisTemplate.opsForValue().set("test","value1",100l, TimeUnit.SECONDS);

        //stringRedisTemplate.opsForValue().set("test","value1",*, TimeUnit.SECONDS);

        // 操作 hash
        stringRedisTemplate.opsForHash();
        //操作list

        stringRedisTemplate.opsForList();

        //操作set
        stringRedisTemplate.opsForSet();
        //操作有序set
        stringRedisTemplate.opsForZSet();


        ///设置过期时间
        stringRedisTemplate.expire("test001",100l,TimeUnit.SECONDS);


        String test = stringRedisTemplate.opsForValue().get("test");


    }
}
