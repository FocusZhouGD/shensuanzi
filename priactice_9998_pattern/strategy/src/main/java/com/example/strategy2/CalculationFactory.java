package com.example.strategy2;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CalculationFactory {

    //可以是goole 的maps。
    //变量
    public final Map<String,CalculationStrategy> map =new HashMap<>(4);


    //需要通过构造函数在容器启动注入到容器中
    public CalculationFactory(Map<String,CalculationStrategy> strategyMap) {
        map.clear();
        map.putAll(strategyMap);
    }

    //从容器中获取
    public Map<String, CalculationStrategy> getMap() {
        return map;
    }
}
