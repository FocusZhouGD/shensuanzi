package com.example.strategy;

import org.springframework.stereotype.Service;

@Service("UnionPay")
public class UnionPay implements IPayment{
    @Override
    public PayResult pay(Order order) {
        return new PayResult("云闪付支付成功");
    }
}
