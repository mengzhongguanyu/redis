package com.mzgy.test;

import com.mzgy.jedis.JedisConnet;
import com.mzgy.pojo.User;
import com.mzgy.serializer.ProtoStuffSerializerUtil;
import com.mzgy.serializer.ProtostuffSerializer;
import redis.clients.jedis.Jedis;

/**
 * Created by mypc on 2017/9/9.
 */
public class UserTest {
    public static void main(String[] args) {
        Jedis jedis = JedisConnet.getJedis();
        User user = new User();
        user.setId(jedis.incr("string:user:seq"));
        user.setName("Tom");
        user.setAge(12);
        user.setInfo("我是学生");
        byte[] bytes = ProtoStuffSerializerUtil.serialize(user);
        String result = jedis.set("string:user:2".getBytes(),bytes);
        System.out.println(result);
        Long start = System.currentTimeMillis();
        byte[] resBytes = jedis.get("string:user:2".getBytes());
        User userResult = ProtoStuffSerializerUtil.deserialize(resBytes, User.class);
        System.out.println(System.currentTimeMillis() - start );
        System.out.println(userResult);
    }
}
