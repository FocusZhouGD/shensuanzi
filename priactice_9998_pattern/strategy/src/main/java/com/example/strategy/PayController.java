package com.example.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

    @Autowired
    private ApplicationContext applicationContext;



    @GetMapping("/pay")
    public PayResult pay(@RequestParam("amount") int amount,@RequestParam("paymentType") String paymentType ){
        Order order = new Order();
        order.setAmount(amount);
        order.setPaymentType(paymentType);

        //根据不同类型获取不同类型的策略
        //这里可以使用枚举以及map映射不同的类也是可以的，放的位置不同使用的场景就可以不一样
        IPayment payment = applicationContext.getBean(paymentType, IPayment.class);
        PayResult payResult = payment.pay(order);
        return payResult;
    }
}
