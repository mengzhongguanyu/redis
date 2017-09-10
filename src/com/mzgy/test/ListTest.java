package com.mzgy.test;

import com.mzgy.jedis.JedisConnet;
import com.mzgy.pojo.User;
import com.mzgy.serializer.ProtoStuffSerializerUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mypc on 2017/9/10.
 */
public class ListTest {
    public static void main(String[] args) {
        Jedis jedis = JedisConnet.getJedis();
        List<User> users = new ArrayList<>();
        User user = null;
        for (int i = 0 ; i < 5 ; i ++){
            user = new User();
            user.setId(jedis.incr("string:user:seq"));
            user.setName("XiaoMing"+i);
            user.setAge(12+i);
            user.setInfo("我是学生"+i);
            jedis.lpush("list:class:1".getBytes(), ProtoStuffSerializerUtil.serialize(user));
        }
        Long start = System.currentTimeMillis();
        List<byte[]> result = jedis.lrange("list:class:1".getBytes(), 0, -1);
        System.out.println("获取list用时："+ (System.currentTimeMillis() - start));
        List<User> users1 = new ArrayList<>();
        result.forEach(bytes -> {
            User usr = ProtoStuffSerializerUtil.deserialize(bytes, User.class);
            users1.add(usr);
        });
        System.out.println("获取共用时用时："+ (System.currentTimeMillis() - start));
        users1.forEach(user1 -> {
            System.out.println(user1.toString());
        });

    }
}
