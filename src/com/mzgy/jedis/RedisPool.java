package com.mzgy.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by mypc on 2017/9/10.
 */
public class RedisPool {
    public static Jedis getRedisPool(){
        Jedis jedis = null;
        JedisPool jedisPool = new JedisPool("127.0.0.1",6379);
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
