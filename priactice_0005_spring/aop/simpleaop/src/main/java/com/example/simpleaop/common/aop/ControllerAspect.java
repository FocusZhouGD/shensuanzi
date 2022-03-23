package com.example.simpleaop.common.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 如何理解aop？
 * 首先aop是基于动态代理实现，如果代理目标是一个接口的就是采用jdk代理实现，如果不是就采用cglib
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect implements Ordered {


    @Pointcut("execution(* com.example.simpleaop.controller..*(..))")
    public void pointcuts() {

    }

    @Around("pointcuts()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //日志记录log
        //日志记录表
        log.info("controller aop start....");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //log.info("from request get attributes result:{}", JSON.toJSONString(requestAttributes));
        log.info("from request get attributes result:{}", requestAttributes);
        ServletRequestAttributes servletRequestAttr = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttr.getRequest();
        request.getHeader("Accept-Charset");
        request.getHeader("Content-Type");
        Object[] args = joinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                sb = sb.append(arg).append("|");
            }

        }


        //请求方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        log.info("<<<<请求的方法是:{}>>>>>>",methodName);
        String name = joinPoint.getSignature().getDeclaringType().getName();
        log.info("<<<<请求的方法是:{}>>>>>>",name);

        //db
        Object proceed = joinPoint.proceed(args);

        // 相当于后置  一前一后


    }

    @Override
    public int getOrder() {
        return 0;
    }
}
