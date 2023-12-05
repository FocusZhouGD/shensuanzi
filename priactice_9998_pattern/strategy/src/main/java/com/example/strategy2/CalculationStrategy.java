package com.example.strategy2;

import org.springframework.stereotype.Component;

/**
 * 策略接口
 */
public interface CalculationStrategy {

    /**
     * 策略通用方法
     *
     * @param num1
     * @param num2
     * @return
     */
    int operate(int num1, int num2);

    //如果不写默认使用名字的驼峰
    @Component("add")
    class AddCalculationStrategyIml implements CalculationStrategy {

        @Override
        public int operate(int num1, int num2) {
            return num1 + num2;
        }
    }

    @Component("subtract")
    class SubtractionStrategyIml implements CalculationStrategy {

        @Override
        public int operate(int num1, int num2) {
            return num1 - num2;
        }
    }

    @Component("multiple")
    class multipleStrategyIml implements CalculationStrategy {

        @Override
        public int operate(int num1, int num2) {
            return num1 * num2;
        }
    }

    @Component("Division")
    class DivisionStrategyIml implements CalculationStrategy {

        @Override
        public int operate(int num1, int num2) {
            return num1 / num2;
        }
    }

}
