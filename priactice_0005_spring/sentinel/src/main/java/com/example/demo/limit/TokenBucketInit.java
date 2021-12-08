package com.example.demo.limit;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Set;

/**
 *  To change this template use File | Settings | File Templates.
 * 初始化令牌桶
 */
@Component
public class TokenBucketInit {

    @Autowired
    RateLimitClient rateLimitClient;

    @PostConstruct
    public void initTokenBucket() {
        String basePackages = "com.api";
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(basePackages) // 指定路径URL
                .addScanners(new MethodAnnotationsScanner() ) // 添加 方法注解扫描工具
        );
        // 获取注解对应的方法
        Set<Method> resources =reflections.getMethodsAnnotatedWith(AccessLimit.class);

        for (Method method: resources) {
            AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
            int maxBurst = accessLimit.maxBurst();
            int rate = accessLimit.rate();
            String limitKey = accessLimit.limitKey();
            rateLimitClient.initToken(limitKey, rate, maxBurst);
        }
    }

}