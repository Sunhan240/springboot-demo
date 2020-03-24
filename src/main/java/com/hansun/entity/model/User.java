/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/16 1:24 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.entity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * 〈一句话功能简述〉<br>
 * TODO(用户测试类)
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/16 1:24 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class User {

    @NotEmpty(message = "用户名不能为空！")
    private String username;

    @NotEmpty(message = "密码不能为空！")
    @Length(min = 6,max = 10,message = "密码不能小于6位且不能超过10位！")
    private String password;

    @Min(value = 15,message = "您必须年满16岁！")
    @Max(value = 80,message = "您不能超过100岁！")
    private Integer age;

    private String address;

    public User(@NotEmpty(message = "用户名不能为空！") String username,
                @NotEmpty(message = "密码不能为空！")
                @Length(min = 6, max = 10, message = "密码不能小于6位且不能超过10位！") String password,
                @Min(value = 15, message = "您必须年满16岁！")
                @Max(value = 80, message = "您不能超过100岁！") Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
