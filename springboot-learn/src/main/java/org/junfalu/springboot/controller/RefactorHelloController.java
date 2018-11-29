package org.junfalu.springboot.controller;

import org.junfalu.api.Entity.User;
import org.junfalu.api.service.HelloService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lujunfa
 * @Date: 2018/11/29 10:59
 * @Description:
 */

@RestController
public class RefactorHelloController implements HelloService {
    @Override
    public String hello(String name) {
        return "hello refacotr" + name;
    }

    @Override
    public User hello(String name, Integer age) {
        return new User(name,age,'m');
    }

    @Override
    public String hello(User user) {
        return user.getName()+user.getAge();
    }
}
