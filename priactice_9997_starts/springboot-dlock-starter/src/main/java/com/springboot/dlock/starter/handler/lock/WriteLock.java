package com.springboot.dlock.starter.handler.lock;

import com.springboot.dlock.starter.model.LockInfo;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

/**
 * @ClassName WriteLock
 * @Description TODO
 * @Author zhouguodong
 * @Date 2021/12/17 15:55
 * @Version 1.0
 **/
public class WriteLock implements Lock{
    private RReadWriteLock rLock;
    private final LockInfo lockInfo;
    private RedissonClient redissonClient;

    public WriteLock(LockInfo lockInfo, RedissonClient redissonClient) {
        this.lockInfo = lockInfo;
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean acquire() {
        return false;
    }

    @Override
    public boolean release() {
        return false;
    }
}
