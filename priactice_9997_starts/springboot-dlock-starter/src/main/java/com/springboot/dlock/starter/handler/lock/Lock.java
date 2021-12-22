package com.springboot.dlock.starter.handler.lock;

/**
 * @ClassName Lock
 * @Description TODO
 * @Author zhouguodong
 * @Date 2021/12/17 14:35
 * @Version 1.0
 **/
public interface Lock {

    boolean acquire();

    boolean release();
}
