package com.example.demo.limit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

@Service
public class RateLimitClient {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Qualifier("getRedisScript")
    @Resource
    RedisScript<Long> ratelimitLua;
    @Qualifier("getInitRedisScript")
    @Resource
    RedisScript<Long> ratelimitInitLua;


    /**
     *
     * @param key
     * @param rate  速率
     * @param burst  桶最大容量
     * @return
     */
    public Token initToken(String key,int rate,int burst){
        Token token = Token.SUCCESS;
        Long currMillSecond = stringRedisTemplate.execute(
                (RedisCallback<Long>) redisConnection -> redisConnection.time()
        );
        /**
         * redis.pcall("HMSET",KEYS[1],
         "last_mill_second",ARGV[1],
         "curr_permits",ARGV[2],
         "max_burst",ARGV[3],
         "rate",ARGV[4],
         "app",ARGV[5])
         */

       /* last_mill_second 最后时间毫秒
        curr_permits 当前可用的令牌
        max_burst 令牌桶最大值
        rate 每秒生成几个令牌
        app 应用*/
        Long accquire = stringRedisTemplate.execute(ratelimitInitLua,
                Collections.singletonList(getKey(key)), currMillSecond.toString(), ""+burst, ""+burst, ""+rate, "skynet");
        if (accquire == 1) {
            token = Token.SUCCESS;
        } else if (accquire == 0) {
            token = Token.SUCCESS;
        } else {
            token = Token.FAILED;
        }
        return token;
    }
    /**
     * 获得key操作
     *
     * @param key
     * @param permits 获取的token数量
     * @return
     */
    public Token accquireToken(String key, Integer permits) {
        Token token = Token.SUCCESS;
        Long currMillSecond = stringRedisTemplate.execute(
                (RedisCallback<Long>) redisConnection -> redisConnection.time()
        );

        Long accquire = stringRedisTemplate.execute(ratelimitLua,
                Collections.singletonList(getKey(key)), currMillSecond.toString(),permits.toString());
        if (accquire == 1) {
            token = Token.SUCCESS;
        } else {
            token = Token.FAILED;
        }
        return token;
    }

    /**
     * 窗口限流获取token
     * @param key
     * @param permits
     * @param seconds
     * @return
     */
    public Token accquireToken(String key, Integer permits,Integer seconds) {
        Token token;
//        Long currMillSecond = stringRedisTemplate.execute(
//                (RedisCallback<Long>) redisConnection -> redisConnection.time()
//        );

        Long accquire = stringRedisTemplate.execute(ratelimitLua,
                Collections.singletonList(getKey(key)), permits.toString(), seconds.toString());
        if (accquire == 1) {
            token = Token.SUCCESS;
        } else {
            token = Token.FAILED;
        }
        return token;
    }

    public String getKey(String key) {
        return Constants.RATE_LIMIT_KEY + key;
    }

}
