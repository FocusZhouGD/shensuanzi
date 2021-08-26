package com.example.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Count2 {
    //可重入读写锁
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void get() {
        try {
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "get begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "get end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void put() {
        try {
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "put begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "pet end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
