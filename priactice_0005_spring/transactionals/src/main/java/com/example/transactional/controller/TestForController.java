package com.example.transactional.controller;

import com.example.transactional.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test for
 */
@RestController
public class TestForController {
    @Autowired
    private ProductService productService;

    @GetMapping("/testFor")
    public String testFor(){
        productService.testForDeal();
        return "ok";
    }


    public static void main(String[] args) {

        booler();

    }

    private static Boolean booler() {
        Boolean flag = false;
        return flag != null && flag;
    }
}
