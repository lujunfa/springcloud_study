package org.junfalu.feign.fallback;

import org.junfalu.api.Entity.User;
import org.junfalu.api.service.HelloService;

/**
 * @Author: lujunfa
 * @Date: 2018/11/29 13:46
 * @Description:基于reign的服务降级类
 */
public class HelloServiceFallBack implements HelloService {
    @Override
    public String hello(String name) {
        return "error";
    }

    @Override
    public User hello(String name, Integer age) {
        return new User("未知",0,'m');
    }

    @Override
    public String hello(User user) {
        return "error";
    }
}
