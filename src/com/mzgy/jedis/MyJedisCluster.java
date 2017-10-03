package com.mzgy.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by mypc on 2017/9/23.
 */
public class MyJedisCluster {

    private static JedisCluster jedisCluster;

    public static JedisCluster getJedisCluster(){
        if (jedisCluster == null){
            jedisCluster = loardJedisCluster();
        }
        return jedisCluster;
    }

    private static synchronized JedisCluster loardJedisCluster(){
        Set<HostAndPort> set = new HashSet<>();
        set.add(new HostAndPort("192.168.220.128",6380));
        set.add(new HostAndPort("192.168.220.128",6381));
        set.add(new HostAndPort("192.168.220.128",6382));
        JedisCluster jedisCluster = new JedisCluster(set);
        return jedisCluster;
    }
}
