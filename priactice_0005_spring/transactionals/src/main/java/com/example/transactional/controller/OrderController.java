package com.example.transactional.controller;

import com.example.transactional.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

@RestController
public class OrderController {


    @Autowired
    private ProductService productService;


    /**
     * 使用CountDownLatch 模拟并发
     */
    @GetMapping("/sell")
    public void sell() {
        CountDownLatch cd = new CountDownLatch(100);

        for (int i = 0; i <= 100; i++) {
            new Thread(() -> {
                try {
                    cd.await();
                    productService.sellProducts();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            cd.countDown();
        }
    }

    @GetMapping("/test")
    public void sellOne() {
        productService.sellProduct();
    }



    @GetMapping("/tests")
    public void sellOnes() {
        productService.sellProducts();
    }


    /**
     * 解决方案一
     */
    @GetMapping("/right1")
    public void right1() {
        CountDownLatch cd = new CountDownLatch(100);
        for (int i = 0; i <= 100; i++) {
            new Thread(() -> {
                try {
                    cd.await();
                    productService.sellRightProducts();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            cd.countDown();
        }
    }



}
