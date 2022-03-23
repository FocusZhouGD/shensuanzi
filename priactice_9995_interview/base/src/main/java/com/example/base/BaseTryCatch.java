package com.example.base;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BaseTryCatch
 * @Description TODO
 * @Author zhouguodong
 * @Date 2022/1/18 9:53
 * @Version 1.0
 **/
public class BaseTryCatch {




    public static void main(String[] args) {


        List<String> objects = Collections.synchronizedList(new ArrayList<>());
        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());


        String s = testFinal();
        System.out.println(s);
    }

    public static String testFinal(){
        try {
            int i = 10 / 0;
            System.out.println("try....");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch。。。。。");
            return "走了catch中的return";
        } finally {
            //return "走了finally中的return";
        }

        return "走了最外层的return";
    }
}
