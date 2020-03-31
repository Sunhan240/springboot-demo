package com.hansun.web.control;

import com.hansun.entity.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * TODO(thymeleaf模板引擎)
 *
 * @author : sunhan
 * @version 1.0
 * @since : 2020/3/6 21:20
 */
@Controller
public class ExampleController {

    @RequestMapping("/")
    public String string(ModelMap map) {
        map.addAttribute("username", "孙寒");
        map.addAttribute("projectName", "SpringBoot Demo");
        map.addAttribute("email", "sunhan240@163.com");
        return "thymeleaf/index";
    }


    @RequestMapping("/if")
    public String ifUnless(ModelMap map) {
        map.addAttribute("flag", "yes");
        return "thymeleaf/if";
    }

    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "thymeleaf/list";
    }

    @RequestMapping("/url")
    public String url(ModelMap map) {
        map.addAttribute("type", "link");
        map.addAttribute("pageId", "springcloud/2017/09/11/");
        map.addAttribute("img", "http://www.ityouknow.com/assets/images/neo.jpg");
        return "thymeleaf/url";
    }

    @RequestMapping("/eq")
    public String eq(ModelMap map) {
        map.addAttribute("name", "neo");
        map.addAttribute("age", 30);
        map.addAttribute("flag", "yes");
        return "thymeleaf/eq";
    }

    @RequestMapping("/switch")
    public String switchCase(ModelMap map) {
        map.addAttribute("sex", "human");
        return "thymeleaf/switch";
    }

    private List<User> getUserList() {
        List<User> list = new ArrayList<>();
        User user1 = new User("大牛", "123456", 19);
        User user2 = new User("小牛", "han.sunsh", 35);
        User user3 = new User("纯洁的微笑", "hidashdads", 45);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }


    @RequestMapping("/testObject")
    public String testThymeleafObject(HttpServletRequest request) {

        request.setAttribute("request", "HttpServletRequest Object set attribute");
        HttpSession session = request.getSession();
        session.setAttribute("sessionId", "this is a session");
        return "thymeleaf/testObject";
    }


    @RequestMapping(value = "/utility")
    public String testUtility(ModelMap map) {
        map.addAttribute("username", "孙寒");
        map.addAttribute("users", getUserList());
        map.addAttribute("count", 15);
        map.addAttribute("date", new Date());
        return "thymeleaf/utility";
    }

}