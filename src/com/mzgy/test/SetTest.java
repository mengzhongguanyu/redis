package com.mzgy.test;

import com.mzgy.jedis.JedisConnet;
import com.mzgy.pojo.User;
import com.mzgy.serializer.ProtoStuffSerializerUtil;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mypc on 2017/9/10.
 */
public class SetTest {
    public static void main(String[] args) {
        Jedis jedis = JedisConnet.getJedis();
        jedis.sadd("set:user:1","b","c");

        Long start = System.currentTimeMillis();
        Set<String> resultSet = jedis.smembers("set:user:1");
        System.out.println("取用时；"+ (System.currentTimeMillis() - start));
        resultSet.forEach(s -> {
            System.out.println(s);
        });

        for (int i = 0;i<3 ; i++) {
            User user = new User();
            user.setId(Long.valueOf(i));
            user.setName("sanmao"+i);
            user.setAge(12);
            jedis.sadd("set:class:2".getBytes(), ProtoStuffSerializerUtil.serialize(user));
        }
        start = System.currentTimeMillis();
        Set<byte[]> userBytes  = jedis.smembers("set:class:2".getBytes());
        System.out.println("取User用时；"+ (System.currentTimeMillis() - start));
        Set<User> users = new HashSet<>();
        userBytes.forEach(bytes -> {
            Long start1 = System.currentTimeMillis();
            User user = ProtoStuffSerializerUtil.deserialize(bytes, User.class);
            System.out.println("反序列用时；"+ (System.currentTimeMillis() - start1));
            users.add(user);
        });
        users.forEach(user -> {
            System.out.println(user.toString());
        });
    }
}
