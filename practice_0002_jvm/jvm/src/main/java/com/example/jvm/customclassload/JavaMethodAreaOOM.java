package com.example.jvm.customclassload;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法区内存溢出
 *
 * 方法区存放类的相关信息，我们可以不断生成新的类信息到方法区，直到撑爆方法区
 * 如何动态产生类信息呢？JavaAPI中有反射， 但是很多框架中都是用CGLib，例如Spring。这里也使用CGLib
 *   没有复现出来
 */
public class JavaMethodAreaOOM {

    static class OOMObject{

    }

    public static void main(String[] args) {
        while (true){

            //创建增强器
            Enhancer enhancer = new Enhancer();
            //设置代理的对象
            enhancer.setSuperclass(OOMObject.class);
            //设置没有缓存 很重要 很快就OOM
            enhancer.setUseCache(false);
            //不增强如何方法
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(0,objects);
                }
            });

            enhancer.create();
        }
    }
}
