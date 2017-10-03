package com.mzgy.test;

import com.mzgy.jedis.JedisConnet;
import com.mzgy.jedis.RedisPool;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by mypc on 2017/9/9.
 */
public class JedisTest {
    public static void main(String[] args){
        Jedis jedis = RedisPool.getRedisPool();
        String result = jedis.get("string:user:1");
        System.out.println(result);
    }
}
