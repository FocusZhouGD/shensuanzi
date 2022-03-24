package com.example.othertest;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class StringReplceTest {

    static final String jsonStr = "{\"name\":\"11\",\"time\":\"2014-10-21\"}";

    static final String template = "亲爱的用户${name},你好，上次登录时间为${time}";

    static String generateWelcome(String jsonStr,String template){
        Gson gson = new Gson();
        HashMap jsonMap = gson.fromJson(jsonStr, HashMap.class);
        for (Object s : jsonMap.keySet()) {
            template = template.replaceAll("\\$\\{".concat(s.toString()).concat("\\}")
                    , jsonMap.get(s.toString()).toString());
        }
        return template;
    }


    public static void main(String[] args) {
        Map<String,Object> mapStr =new HashMap<>();
        mapStr.put("name","大仙");
        mapStr.put("time","2022-03-24");
        String s = JSONObject.toJSONString(mapStr);
        System.out.println("map转为string"+s);
        System.out.println(generateWelcome(s,template));

        System.out.println(generateWelcome(jsonStr,template));
    }
}
