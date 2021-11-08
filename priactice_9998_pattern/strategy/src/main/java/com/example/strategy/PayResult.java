package com.example.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 支付结果类
 *
 */

@Data
@AllArgsConstructor
public class PayResult {
    /**
     * 支付结果
     */
    private String result;
}
