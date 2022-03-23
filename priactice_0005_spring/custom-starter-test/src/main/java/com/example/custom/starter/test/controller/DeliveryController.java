package com.example.custom.starter.test.controller;



import com.zgd.redis.sentinel.starter.limit.AccessLimit;
import com.zgd.redis.sentinel.starter.limit.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class DeliveryController {

//    @Autowired
//    private RedisConfig redisConfig;

    @GetMapping("/getExpress1")
    @AccessLimit(rate = 1,maxBurst = 1,limitKey = "getExpressCe1")
    public String getExpressCe1() {
        return "ok";
    }
}
