package com.hansun.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomPropertiesUtilTest {


    @Value("${appStore}")
    private String title;

    @Value("${jdbc_url}")
    private String url;


    @Test
    public void testGet(){
        System.out.println(title);
        System.out.println(url);
    }
}