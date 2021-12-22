package com.springboot.dlock.starter.enums;
/**
 * @Description: 锁的类型
 * @Author: zhouguodong
 * @Date:
 **/
public enum LockTypeEnum {

    /**
     * 可重入锁
     */
    Reentrant,
    /**
     * 公平锁
     */
    FairLock,
    /**
     * 读锁
     */
    ReadLock,
    /**
     * 写锁
     */
    WriteLock;

    LockTypeEnum() {
    }
}
