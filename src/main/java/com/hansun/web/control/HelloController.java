package com.hansun.web.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hansun.entity.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : sunhan
 * @version 1.0
 * @since : 2020/3/4 23:20
 */
@RestController
public class HelloController {

    private Logger log = LogManager.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String test1(String name) {
        log.info("进入了hello word测试接口。。。。。");
        return "HelloWorld！" + name + "，欢迎来到springboot世界!";
    }


    @GetMapping(value = "get/{name}")
    public String get(@PathVariable String name) {
        return name;
    }

    /**
     * javax.validation 注解object进行字段校验
     *
     * @param user   传输的对象
     * @param result 校验的结果
     * @return response
     */
    @RequestMapping(value = "/saveUser")
    public JSONObject saveUser(@Valid User user, BindingResult result) {
        log.info("user:{}", user);
        JSONObject json = new JSONObject();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                json.put(error.getObjectName(), error.getDefaultMessage());
            }
            return json;
        }
        return JSON.parseObject(JSON.toJSONString(user));
    }

}
