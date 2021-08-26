package com.example.conc;

public class Counter {
    public final static int B = 10;
    private volatile int sum = 0;

    public synchronized void incr() {
        sum = sum + 1;
    }

    public int getNum() {
        return sum;
    }

    public static void main(String[] args) {
        int loop = 10000;
        Counter counter = new Counter();
        for (int i = 0; i < loop; i++) {
            counter.incr();
        }

        final Counter counter2 =new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
        });
        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
