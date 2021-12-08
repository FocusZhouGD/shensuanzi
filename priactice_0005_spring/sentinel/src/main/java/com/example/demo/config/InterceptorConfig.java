package com.example.demo.config;


import com.example.demo.intercepter.AccessLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * 不能继承 WebMvcConfigurerSupper  会导致其他的拦截不生效
 *
 *
 */

@Component
public class InterceptorConfig implements WebMvcConfigurer {

    public InterceptorConfig(AccessLimitInterceptor accessLimitInterceptor) {
        this.accessLimitInterceptor = accessLimitInterceptor;
    }

    @Autowired
    private AccessLimitInterceptor accessLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLimitInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");
        //  不能使用 super 继承
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
