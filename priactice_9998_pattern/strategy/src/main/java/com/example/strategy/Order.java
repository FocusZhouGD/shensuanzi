package com.example.strategy;

import lombok.Data;

@Data
public class Order {
    /**
     * 金额
     */
    private int amount;

    /**
     * 支付类型
     */
    private String paymentType;
}
