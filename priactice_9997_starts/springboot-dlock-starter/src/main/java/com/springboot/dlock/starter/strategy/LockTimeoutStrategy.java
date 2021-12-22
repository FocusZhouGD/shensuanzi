package com.springboot.dlock.starter.strategy;

import com.springboot.dlock.starter.exception.DlockTimeoutException;
import com.springboot.dlock.starter.handler.lock.Lock;
import com.springboot.dlock.starter.handler.lock.LockTimeoutHandler;

import com.springboot.dlock.starter.model.LockInfo;
import org.aspectj.lang.JoinPoint;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName LockTimeoutStrategy
 * @Description 锁超时策略
 * @Author zhouguodong
 * @Date 2021/12/15 15:06
 * @Version 1.0
 **/
public enum LockTimeoutStrategy implements LockTimeoutHandler {

    /**
     * 无操作
     */
    NO_OPERATION() {
        @Override
        public void handle(LockInfo lockInfo, Lock lock, JoinPoint joinPoint) {

        }
    },
    /**
     * 快速失败
     */
    FAIL_FAST() {
        @Override
        public void handle(LockInfo lockInfo, Lock lock, JoinPoint joinPoint) {
            String errMsg = String.format("Failed to acquire Lock(%s) with timeout(%ds)", lockInfo.getName(), lockInfo.getWaitTime());
            throw new DlockTimeoutException(errMsg);
        }
    },
    /**
     * 一直阻塞，知道获得锁，在太多的尝试后，任会报错
     */
    KEEP_ACQUIRE() {
        private static final long DEFAULT_INTERVAL = 100L;

        private static final long DEFAULT_MAX_INTERVAL = 3 * 60 * 1000L;

        @Override
        public void handle(LockInfo lockInfo, Lock lock, JoinPoint joinPoint) {
            long interval = DEFAULT_INTERVAL;
            while (!lock.acquire()) {
                if (interval > DEFAULT_MAX_INTERVAL) {
                    String errMsg = String.format("Failed to acquire Lock(%s) after too many times, this may because dead lock occurs.", lockInfo.getName());
                    throw new DlockTimeoutException(errMsg);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(interval);
                } catch (InterruptedException e) {
                    throw new DlockTimeoutException("Failed to acquire Lock", e);
                }

            }

        }
    }

}
