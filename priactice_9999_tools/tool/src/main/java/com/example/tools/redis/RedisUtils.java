package com.example.tools.redis;

import java.util.HashMap;

/**
 * @ClassName RedisUtils
 * @Description TODO
 * @Author zhouguodong
 * @Date 2021/12/30 17:02
 * @Version 1.0
 **/
public class RedisUtils {
    /**
     * 使用标准的key  fh-open-platform:user
     *
     *
     * 解析可以使用  JSON.parseObject(redisUtil.get(redisKey), LoginUserDto.class)
     */


    public static void main(String[] args) {
        HashMap map =new HashMap();
        map.put(null,"kongValue");
        map.put(null,"eRongValue");

        Object o = map.get(null);
        System.out.println(o);
    }
}
