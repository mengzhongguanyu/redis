package com.mzgy.test;

import com.alibaba.fastjson.JSONObject;
import com.mzgy.jedis.RedisPool;
import com.mzgy.pojo.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by mypc on 2017/9/10.
 */
public class PoolTest {
    @Test
    public void test1(){
        Jedis jedis = RedisPool.getRedisPool();
        jedis.set("set:user:2", "testes");
        Long start = System.currentTimeMillis();
        String str = jedis.get("set:user:2");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(str);
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

}
