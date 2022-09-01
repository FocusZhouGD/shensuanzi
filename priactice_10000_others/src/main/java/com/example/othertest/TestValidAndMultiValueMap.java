package com.example.othertest;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TestValidAndMultiValueMap {
    public static void main(String[] args) {


        /**
         * https://blog.csdn.net/qq_32258777/article/details/86743416
         */

        //结合同意异常




        //一个key 多个value
        MultiValueMap<String,String> multiValueMap =new LinkedMultiValueMap<>();
        multiValueMap.add("menu","1");
        multiValueMap.add("menu","2");
        multiValueMap.add("m","s");
        System.out.println(multiValueMap);
    }
}
