package com.springboot.dlock.starter.handler.lock;

import com.springboot.dlock.starter.model.LockInfo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ReentrantLock
 * @Description TODO
 * @Author zhouguodong
 * @Date 2021/12/17 14:56
 * @Version 1.0
 **/
public class ReentrantLock implements Lock {

    // 全部变量锁
    private RLock rLock;

    private final LockInfo lockInfo;

    private RedissonClient redissonClient;


    public ReentrantLock(RedissonClient redissonClient, LockInfo lockInfo) {
        this.redissonClient = redissonClient;
        this.lockInfo = lockInfo;
    }

    @Override
    public boolean acquire() {
        try {
            rLock = redissonClient.getLock(lockInfo.getName());
            //重试
            return rLock.tryLock(lockInfo.getWaitTime(), lockInfo.getReleaseTime(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public boolean release() {
        //检查当前线程是否持有锁
        if (rLock.isHeldByCurrentThread()){
            try {
                return rLock.forceUnlockAsync().get();
            } catch (InterruptedException e) {
                return false;
            } catch (ExecutionException e) {
                return false;
            }
        }
        return false;
    }
}
