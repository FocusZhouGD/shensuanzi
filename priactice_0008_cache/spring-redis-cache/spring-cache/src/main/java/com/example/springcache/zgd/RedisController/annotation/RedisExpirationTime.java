package com.example.springcache.zgd.RedisController.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented//可以去掉
public @interface RedisExpirationTime {

    /**
     *  缓存过期时间 秒，默认值设置为2小时
     */
    long value() default 10800L;
}
