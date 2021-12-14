package com.example.redis.start.test;

import com.example.redis.start.test.service.TestRedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisStartTestApplicationTests {
    @Autowired
    private TestRedisService service;

    @Test
    void contextLoads() {
        service.testRedisStart();
    }

}
