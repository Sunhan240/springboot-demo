package com.hansun.web.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : sunhan
 * @since : 2020/3/4 23:20
 * @version 1.0
 */
@RestController
public class LogisticController {

    private Logger log = LogManager.getLogger(LogisticController.class);

    @RequestMapping("/test1")
    public String test1(String name) {
        log.info("进入了hello word测试接口。。。。。");
        return "HelloWorld," + name + "欢迎来到springboot世界!";
    }

}
