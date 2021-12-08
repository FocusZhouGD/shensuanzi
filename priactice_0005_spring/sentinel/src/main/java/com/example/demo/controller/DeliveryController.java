package com.example.demo.controller;


import com.example.demo.limit.AccessLimit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/delivery")
@Slf4j
public class DeliveryController {

    @GetMapping("/getExpress1")
    @AccessLimit(rate = 1,limitKey = "getExpressCe1")
    public String getExpressCe1() {
        return "ok";
    }
}
