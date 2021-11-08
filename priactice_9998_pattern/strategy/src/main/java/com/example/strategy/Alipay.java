package com.example.strategy;

import org.springframework.stereotype.Service;

@Service("Alipay")
public class Alipay implements IPayment{
    @Override
    public PayResult pay(Order order) {
        return new PayResult("阿里支付成功");
    }
}
