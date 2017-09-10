package com.mzgy.test;

import com.mzgy.jedis.JedisConnet;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mypc on 2017/9/10.
 */
public class HashTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "sanmao");
        map.put("age", "12");

        Jedis jedis = JedisConnet.getJedis();
        Long start = System.currentTimeMillis();
        map.keySet().stream().forEach(s -> {
            jedis.hset("hash:user:1", s, map.get(s));
        });
        System.out.println("执行存操作时间：" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        Map<String, String> resultMap = jedis.hgetAll("hash:user:1");
        System.out.println("执行取操作时间：" +(System.currentTimeMillis() - start));
        resultMap.keySet().forEach(s -> {
            System.out.println("Key is: " + s + " value is: " + resultMap.get(s));
        });
    }
}
