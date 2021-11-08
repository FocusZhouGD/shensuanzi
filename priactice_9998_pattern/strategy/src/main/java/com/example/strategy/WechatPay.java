package com.example.strategy;

import org.springframework.stereotype.Service;

@Service("WechatPay")
public class WechatPay implements IPayment {


    @Override
    public PayResult pay(Order order) {
        return new PayResult("微信支付成功");
    }
}
