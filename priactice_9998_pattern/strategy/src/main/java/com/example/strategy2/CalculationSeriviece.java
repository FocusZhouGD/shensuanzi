package com.example.strategy2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationSeriviece {

    @Autowired
    private CalculationFactory calculationFactory;


    public int operateByStrategy(String strategy,int num1,int num2){
        //获取入参，根据不同的参数执行不同的策略，context的get方法是在这个地方用到的，
        //calculationFactory.getMap().get(strategy) 这里可能为空，所以要做一个容错处理
        return calculationFactory.getMap().get(strategy).operate(num1,num2);
    }
}
