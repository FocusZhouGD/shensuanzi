package com.example.base;

import java.lang.reflect.Field;

/**
 * @ClassName FinalReflectDemo
 * @Description final 关键字 关于反射的问题
 * @Author zhouguodong
 * @Date 2022/1/19 17:19
 * @Version 1.0
 **/
public class FinalReflectDemo {


    /**常量：默认值null*/
    private final String v1 = null;
    /**常量：默认值v4*/
    private final String v2 = "v2";

    public String getV1() {
        return v1;
    }

    public String getV2() {
        return v2;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        FinalReflectDemo finalReflectDemo =new FinalReflectDemo();
        Field v1 = finalReflectDemo.getClass().getDeclaredField("v1");
        Field v2 = finalReflectDemo.getClass().getDeclaredField("v2");
//        Field v1 = FinalReflectDemo.class.getDeclaredField("v1");
//        Field v2 = FinalReflectDemo.class.getDeclaredField("v1");


        v1.setAccessible(true);
        v2.setAccessible(true);


        System.out.println("v1 改变前的值（对象取值）：" + finalReflectDemo.getV1());
        //反射改变v1的值
        v1.set(finalReflectDemo,"new_v1");

        System.out.println("v1 改变后的值（对象取值）：" + finalReflectDemo.getV1());
        System.out.println("v1 改变后的值（反射取值）：" + v1.get(finalReflectDemo));

       //反射改变v2的值

        v2.set(finalReflectDemo,"new_v2");

        System.out.println("v2 改变后的值（对象取值）：" + finalReflectDemo.getV2());
        System.out.println("v2 改变后的值（反射取值）：" + v2.get(finalReflectDemo));



    }


    /**
     * 结论：对于非静态的final成员变量，在没有赋值的情况下是可以使用反射对其进行赋值的；
     * 对于已经初始化赋值的变量，反射不能真正该变变量的值，但是使用反射get是可以获取到改变后的值，用实例是无法获取到的。
     */



}
