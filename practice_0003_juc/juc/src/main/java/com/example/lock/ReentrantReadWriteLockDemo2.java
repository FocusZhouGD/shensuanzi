package com.example.lock;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo2 {
    private final HashMap<String, Object> map = new HashMap<>();
    private final ReentrantReadWriteLock rwRiteLock = new ReentrantReadWriteLock();

    public void readWrite(String key){
        Object value=null;
        System.out.println("1.首先开启读锁去内存缓存中取数据。。。");
        rwRiteLock.readLock().lock();
        try {
            value = map.get(key);
            if (value==null){
                System.out.println("2.数据不存在，则释放读锁，开启写锁。。。");
                rwRiteLock.readLock().unlock();
                try {
                    rwRiteLock.writeLock().lock();
                    if (value==null){
                        value="aaaa";
                    }
                } finally {
                    System.out.println("3.释放写锁。。。");
                }
                System.out.println("4.开启读锁。。。");
                rwRiteLock.readLock().lock();
            }
        } finally {
            System.out.println("5.释放读锁");
            rwRiteLock.readLock().unlock();

        }
    }


    public static void main(String[] args) {
        ReentrantReadWriteLockDemo2 demo2 =new ReentrantReadWriteLockDemo2();
        demo2.readWrite("bbbb");

    }


}
