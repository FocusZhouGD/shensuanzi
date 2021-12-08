package com.example.demo.intercepter;


import com.example.demo.limit.AccessLimit;
import com.example.demo.limit.RateLimitClient;
import com.example.demo.limit.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@Component
public class AccessLimitInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(AccessLimitInterceptor.class);

    @Autowired
    RateLimitClient rateLimitClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            // Handler 是否为 HandlerMethod 实例
            if(handler instanceof HandlerMethod){
                // 强转
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                // 获取方法
                Method method = handlerMethod.getMethod();
                // 是否有AccessLimit注解
                if(!method.isAnnotationPresent(AccessLimit.class)){
                    return true;
                }
                // 获取注解内容信息
                AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
                if(accessLimit == null){
                    return true;
                }

                //用于窗口限流
//                int maxTimes = accessLimit.times();//最大请求次数
//                int second = accessLimit.second();//请求时间范围
                //用于窗口限流
                String key =  accessLimit.limitKey();
                //窗口限流
//                Token token = rateLimitClient.accquireToken(key,maxTimes,second);

                int maxTimes = accessLimit.perToken();//每次获取的token数量
                //令牌桶限流
                Token token = rateLimitClient.accquireToken(key,maxTimes);
                if (token.isSuccess()) {
                    return true;
                } else {
                    logger.info("接口被限流：{}" ,key);
                    throw new BaseException(429,"网络异常");
                }
            }
        }catch (BaseException e){
            //throw e;
        } catch (Exception e){
            logger.error("API请求限流拦截异常",e);
            throw new BaseException(429,e.getMessage());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

