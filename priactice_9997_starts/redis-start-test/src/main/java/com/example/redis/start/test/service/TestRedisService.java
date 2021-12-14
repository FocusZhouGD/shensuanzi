package com.example.redis.start.test.service;

import com.example.redis.start.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestRedisService {
    @Autowired
    private RedisUtils redisUtils;

    public void testRedisStart(){
        try {
            Long this_is_value = redisUtils.addSet("start" + "one", "this is value");
            System.out.println(this_is_value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
