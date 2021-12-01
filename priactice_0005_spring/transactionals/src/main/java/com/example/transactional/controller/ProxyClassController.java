package com.example.transactional.controller;

import com.example.transactional.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring aop dymic proxy
 *
 *
 * 1、连接点 JoinPoint
 * 2、切入点 PointCut
 * 3、通知 Advice
 * 4、切面 Aspect
 * 5、织入 Weaving
 * 6、代理
 * 7、目标对象
 */
@RestController
public class ProxyClassController {


    @Autowired
    private ProductService productService;


    @GetMapping("/proxy")
    public void test(){
        System.out.println("in");
        productService.sellProduct();
        System.out.println();

    }
}
