package com.mzgy.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by mypc on 2017/9/10.
 */
public class RedisPool {

    private static  Jedis jedis;

    public static Jedis getRedisPool(){
        if (jedis != null){
            return jedis;
        } else {
            jedis = getJedisPool();
            return jedis;
        }
    }
    private static synchronized Jedis getJedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.220.128",6379);
        try{
            jedis = jedisPool.getResource();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return jedis;
    }
}
