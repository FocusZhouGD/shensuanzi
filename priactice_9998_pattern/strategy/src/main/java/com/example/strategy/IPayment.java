package com.example.strategy;

/**
 * 支付接口类
 * 支付策略，微信支付、支付宝、云闪付等支付实现类都实现这个接口
 */
public interface IPayment {

    PayResult pay(Order order);
}
