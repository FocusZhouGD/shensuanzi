package com.springboot.dlock.starter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName DlockKey
 * @Description TODO
 * @Author zhouguodong
 * @Date 2021/12/15 9:48
 * @Version 1.0
 **/
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface DlockKey {
    String value() default "";
}
