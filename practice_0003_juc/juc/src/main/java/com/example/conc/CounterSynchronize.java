package com.example.conc;

/**
 * 测试 synchronize
 */
public class CounterSynchronize {

    private volatile int sum = 0;

    public synchronized void incr() {
        sum = sum + 1;
    }

    public void test() {
        synchronized (this) {
            sum = sum + 2;
        }
    }
}
