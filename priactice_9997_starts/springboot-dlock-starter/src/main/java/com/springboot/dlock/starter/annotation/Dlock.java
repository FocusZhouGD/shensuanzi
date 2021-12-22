package com.springboot.dlock.starter.annotation;

import com.springboot.dlock.starter.enums.LockTypeEnum;
import com.springboot.dlock.starter.strategy.LockTimeoutStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Dlock
 * @Description 加锁注解
 * @Author zhouguodong
 * @Date 2021/12/15 9:48
 * @Version 1.0
 **/
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Dlock {

    /**
     * 锁的名字
     *
     * @return name
     */
    String name() default "";

    /**
     * 锁的类型，默认是可重入锁
     *
     * @return LockTypeEnum
     */
    LockTypeEnum lockType() default LockTypeEnum.Reentrant;

    /**
     * 尝试加锁 最多等待时间
     *
     * @return long
     */
    long waitTime() default Long.MIN_VALUE;

    /**
     * 多少时间后自动解锁
     * @return
     */

    long releaseTime() default Long.MIN_VALUE;

    /**
     * 自定义业务key
     * @return
     */

    String[] keys() default {};

    /**
     * 释放锁超时的处理策略
     * @return
     */
    LockTimeoutStrategy releaseTimeoutStrategy() default LockTimeoutStrategy.NO_OPERATION;

    /**
     * 加锁超时策略
     * @return
     */
    LockTimeoutStrategy lockTimeoutStrategy() default  LockTimeoutStrategy.NO_OPERATION;

    /**
     * 自定义加锁超时的处理策略
     * @return
     */
    String customTimeoutLockStrategy() default "";


    /**
     * 自定义解锁超时的处理策略
     * @return
     */
    String releaseTimeoutLockStrategy() default "";




}
