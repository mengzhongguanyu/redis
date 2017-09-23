package com.mzgy.test;

import com.alibaba.fastjson.JSONObject;
import com.jetair.pip.shopping.io.flightShopping.FlightShoppingResponse;
import com.mzgy.jedis.MyJedisCluster;
import com.mzgy.jedis.RedisPool;
import com.mzgy.serializer.ProtoStuffSerializerUtil;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.util.JedisClusterHashTagUtil;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by mypc on 2017/9/11.
 */
public class TestMy {
    public static void main(String[] args) {
        JedisCluster jedisCluster = MyJedisCluster.getJedisCluster();
        Long aLong = jedisCluster.sadd("set:user:1", "user1","user2");
        System.out.println(aLong);
        Set<String> strings = jedisCluster.smembers("set:user:1");
        for (String string : strings) {
            System.out.println(string);
        }

    }
}
