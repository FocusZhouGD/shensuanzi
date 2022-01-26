package com.example.base;

import java.lang.reflect.Field;

/**
 * @ClassName FinalStatusReflectDemo
 * @Description TODO
 * @Author zhouguodong
 * @Date 2022/1/19 17:30
 * @Version 1.0
 **/
public class FinalStatusReflectDemo {


    /**
     * 常量：默认值null
     */
    private static final String v1 = null;
    /**
     * 常量：默认值v4
     */
    private static final String v2 = "v2";

    public static String getV1() {
        return v1;
    }

    public static String getV2() {
        return v2;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Field f1 = FinalStatusReflectDemo.class.getDeclaredField("v1");
        Field f2 = FinalStatusReflectDemo.class.getDeclaredField("v2");
        f1.setAccessible(true);
        f2.setAccessible(true);
//        System.out.println("v1 改变前的值（对象取值）：" + FinalStatusReflectDemo.getV1());
//        f1.set(FinalStatusReflectDemo.class, "new_v1");
//        System.out.println("v1 改变后的值（对象取值）：" + FinalStatusReflectDemo.getV1());
//        System.out.println("v1 改变后的值（反射取值）：" + f1.get(FinalStatusReflectDemo.class));


        //f2

        System.out.println("v2 改变前的值（对象取值）：" + FinalStatusReflectDemo.getV2());
        f2.set(FinalStatusReflectDemo.class, "new_v2");
        System.out.println("v2 改变后的值（对象取值）：" + FinalStatusReflectDemo.getV2());
        System.out.println("v2 改变后的值（反射取值）：" + f2.get(FinalStatusReflectDemo.class));


        /**
         * 对静态的final修饰的字段进行修改 报错，但是可以反射拿到
         */
    }
}
