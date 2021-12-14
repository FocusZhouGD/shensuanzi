package com.example.redis.start.test.controller;

import com.example.redis.start.test.service.TestRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestRedisService service;

    @GetMapping("/get")
    public String test(){
        service.testRedisStart();
        return "ok";
    }
}
