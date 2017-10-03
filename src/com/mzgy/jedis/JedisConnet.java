package com.mzgy.jedis;

import redis.clients.jedis.Jedis;

import java.awt.datatransfer.FlavorListener;

/**
 * Created by mypc on 2017/9/9.
 */
public class JedisConnet {
    private static Jedis jedis;
    public static Jedis getJedis(){
        try {
            jedis = new Jedis("192.168.83.131", 6379);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return jedis;
    }

}
