package com.example.tools.json;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.Map;

public class JsonTransant {


    public static void main(String[] args) {


        Map<Integer,Long> map =new HashMap<>();
        map.put(0,0l);
        map.put(1,1l);

        String s = JSON.toJSONString(map);
        Map<Integer, Long> parseObjectMap =new HashMap<>();

        //  TypeReference 可以专为list ，map 以及对象
        parseObjectMap = JSON.parseObject(s, new TypeReference<Map<Integer, Long>>() {
        });

        Long aLong = parseObjectMap.get(1);
        System.out.println(aLong);

    }
}
