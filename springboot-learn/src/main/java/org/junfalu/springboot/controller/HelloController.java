package org.junfalu.springboot.controller;

import org.junfalu.springboot.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: lujunfa
 * @Date: 2018/11/15 09:32
 * @Description:
 */

@RestController
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private DiscoveryClient client; //eureka服务发现客户端

    @RequestMapping("/hello")
    public String index(){
        ServiceInstance serviceInstance = client.getLocalServiceInstance();

        logger.info("/hello.host："+serviceInstance.getHost()+",service_id=" + serviceInstance.getServiceId());
        return "hello";
    }

    @RequestMapping("/getUser")
    public User getUser(String name){
        if("lujunfa".equals(name))
        return  User.init();
        else return null;
    }

    @RequestMapping("/getUsers")
    public List<User> getUsers(@RequestParam("userId") String userId){
        return Arrays.asList(new User("lujunchao",17,'m'),
                new User("ludesheng",50,'m'),
                new User("nixi",23, 'f'));
    }
}
