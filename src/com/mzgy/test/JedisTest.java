package com.mzgy.test;

import com.mzgy.jedis.JedisConnet;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by mypc on 2017/9/9.
 */
public class JedisTest {
    public static void main(String[] args){
        Jedis jedis = JedisConnet.getJedis();
        Set<String> set = jedis.keys("*");
        for (String str: set) {
            System.out.println(str);
        }
        Set<String> strings = jedis.smembers("set:user:1");
        strings.forEach(s -> {
            System.out.println(s);
        });
    }
}
