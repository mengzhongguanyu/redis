package com.mzgy.test;

import com.alibaba.fastjson.JSONObject;
import com.mzgy.jedis.RedisPool;
import com.mzgy.pojo.User;
import com.mzgy.serializer.ProtoStuffSerializerUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Set;

/**
 * Created by mypc on 2017/9/10.
 */
public class PoolTest {
    @Test
    public void test1(){
        Jedis jedis = RedisPool.getRedisPool();
        Set<String> set =  jedis.keys("*");
        for (String s : set) {
            System.out.println(s);
        }
        Pipeline pipeline = jedis.pipelined();
        for (String s : set) {
            pipeline.del(s);
        }
        pipeline.sync();
        set = jedis.keys("*");
        for (String s : set) {
            System.out.println(s);
        }
    }
    @Test
    public void userTest() {
        Jedis jedis = RedisPool.getRedisPool();
        User user = new User();
        user.setId(1L);
        user.setName("tom");
        user.setAge(12);
        user.setInfo("我是老大");
        String json = JSONObject.toJSONString(user);
        jedis.set("set:user:3", json);
        Long start = System.currentTimeMillis();
        String resultJson = jedis.get("set:user:3");
        User user1 = JSONObject.parseObject(resultJson, User.class);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(user1.toString());
    }

    @Test
    public void usetTest3() {
        Jedis jedis = RedisPool.getRedisPool();
        User user = new User();
        user.setId(1L);
        user.setName("tom");
        user.setAge(12);
        user.setInfo("我是老大");
        byte[] bytes = ProtoStuffSerializerUtil.serialize(user);
        jedis.set("set:user:4".getBytes(), bytes);
        Long start = System.currentTimeMillis();
        byte[] result = jedis.get("set:user:4".getBytes());
        User user1 = ProtoStuffSerializerUtil.deserialize(result,User.class);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(user1.toString());
    }

}
