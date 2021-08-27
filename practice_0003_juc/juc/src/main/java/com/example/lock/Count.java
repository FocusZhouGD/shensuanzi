package com.example.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 */
public class Count {

    final ReentrantLock lock = new ReentrantLock();

    public void get() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "get begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "get end");
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " un lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void put() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "pet begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "pet end");
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " un lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
