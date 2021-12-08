package com.example.demo.limit;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
/*

    //指定second 时间内 API请求次数
    int times() default 0;

    // 请求次数的指定时间范围  秒数(redis数据过期时间)
    int second() default 10;
*/

    //每次获取的令牌数量
    int perToken() default 1;

    //令牌桶最大值，并发量峰值
    int maxBurst() default 200;

    //每秒生成几个令牌，处理速度
    int rate();

    //限流key
    String limitKey();
}
