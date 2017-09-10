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
        String str = jedis.set("string:user:1","java");
        System.out.println(str);
    }
}
