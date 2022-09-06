package com.example.springcache.zgd.RedisController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testRedis")
public class TestRedisController {

    @Autowired
    private TestRedisService testRedisService;

    public void addRedis(){
        testRedisService.addRedis();
    }


}
