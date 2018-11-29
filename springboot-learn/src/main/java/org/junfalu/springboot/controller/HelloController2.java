package org.junfalu.springboot.controller;

import org.junfalu.springboot.Entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lujunfa
 * @Date: 2018/11/29 10:23
 * @Description:
 */

@RestController
public class HelloController2 {
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String   hello(@RequestParam String name){
        return "hello " + name;
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    public User hello3 (@RequestHeader String name , @RequestHeader int age){
        return new User(name,age,'m');
    }

    @RequestMapping(value = "/hello4", method = RequestMethod.POST)
    public String hello4 (@RequestBody User user){
        return user.getName()+" "+ user.getAge();
    }
}
