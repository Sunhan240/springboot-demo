/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/25 5:03 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.redis;

import com.hansun.entity.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/25 5:03 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("username", "sunhan");
        System.out.println(opsForValue.get("username"));
    }

    @Test
    public void testObject() {
        User user = new User("han.sun", "sunhanpassword", 25);
        ValueOperations<String, User> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("han.sunId", user, 2000L,TimeUnit.MILLISECONDS);
        User user1 = opsForValue.get("han.sunId");
        if (user1 != null) {
            System.out.println(user1.toString());
        }
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User user2 = opsForValue.get("han.sunId");
        if (user2!= null) {
            System.out.println(user2.toString());
        }else {
            System.out.println("cache expired！");
        }

    }


    @Test
    public void testList(){
        ListOperations<String,String> listOperations = redisTemplate.opsForList();
        listOperations.leftPushAll("listtest1", "sunhan1", "sunhan2", "sunhan3");

        System.out.println(listOperations.range("listtest1", 0, -1));

        Boolean delete = redisTemplate.delete("listtest1");

        System.out.println(listOperations.range("listtest1", 0, -1));
    }

}
