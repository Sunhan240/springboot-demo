/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/30 9:20 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.web.control;

import com.hansun.annotation.ResetBody;
import com.hansun.common.result.CommonCode;
import com.hansun.entity.model.User;
import com.hansun.exception.ExceptionCast;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/30 9:20 PM
 */
@RestController
public class TestController {


    @ResetBody
    @RequestMapping("/test1")
    public User test1(String name,String password,String age){
        User user = new User();
        user.setAddress("172.16.1.163");
        user.setAge(Integer.parseInt(age));
        user.setUsername(name);
        user.setPassword(password);
        if (user.getAge()==0) {
            ExceptionCast.castCustomExp(
                    CommonCode.UNKNOWNERROR);
        }
        return user;
    }

}
