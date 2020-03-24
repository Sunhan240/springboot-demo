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
        return "index";
    }




    @RequestMapping("/if")
    public String ifUnless(ModelMap map) {
        map.addAttribute("flag", "yes");
        return "if";
    }

    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }

    @RequestMapping("/url")
    public String url(ModelMap map) {
        map.addAttribute("type", "link");
        map.addAttribute("pageId", "springcloud/2017/09/11/");
        map.addAttribute("img", "http://www.ityouknow.com/assets/images/neo.jpg");
        return "url";
    }

    @RequestMapping("/eq")
    public String eq(ModelMap map) {
        map.addAttribute("name", "neo");
        map.addAttribute("age", 30);
        map.addAttribute("flag", "yes");
        return "eq";
    }

    @RequestMapping("/switch")
    public String switchCase(ModelMap map) {
        map.addAttribute("sex", "human");
        return "switch";
    }

    private List<User> getUserList(){
        List<User> list=new ArrayList<>();
        User user1=new User("大牛","123456",19);
        User user2=new User("小牛","han.sunsh",35);
        User user3=new User("纯洁的微笑","hidashdads",45);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return  list;
    }


    @RequestMapping("/testObject")
    public String testThymeleafObject(HttpServletRequest request){

        request.setAttribute("request","HttpServletRequest Object set attribute");
        HttpSession session = request.getSession();
        session.setAttribute("sessionId", "this is a session");
        return "testObject";
    }


    @RequestMapping(value = "/utility")
    public String  testUtility(ModelMap map){
        map.addAttribute("username", "孙寒");
        map.addAttribute("users", getUserList());
        map.addAttribute("count", 15);
        map.addAttribute("date", new Date());
        return "utility";
    }

}