package com.springboot.dlock.starter.handler.lock;

import com.springboot.dlock.starter.model.LockInfo;
import org.aspectj.lang.JoinPoint;


/**
 * @ClassName LockTimeoutHandler
 * @Description TODO
 * @Author zhouguodong
 * @Date 2021/12/15 15:07
 * @Version 1.0
 **/
public interface LockTimeoutHandler {
    void handle(LockInfo lockInfo,Lock lock, JoinPoint joinPoint);
}
