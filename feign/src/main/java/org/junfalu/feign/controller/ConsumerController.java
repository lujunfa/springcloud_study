package org.junfalu.feign.controller;

import org.junfalu.feign.Entity.User;
import org.junfalu.feign.feignService.HelloService;
import org.junfalu.feign.feignService.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lujunfa
 * @Date: 2018/11/28 09:52
 * @Description:
 */

@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @Autowired
    RefactorHelloService refactorHelloService;

    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String feignConsummer(){
        return helloService.hello();
    }

    @RequestMapping(value = "/feign-consumer2",method = RequestMethod.GET)
    public String feignConsummer2(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(helloService.hello2("lujunfa")).append("\n")
                .append(helloService.hello3("lujunchao",17)).append("\n")
                .append(helloService.hello4(new User("liangdongxiu",41,'f'))).append("\n");
        return stringBuilder.toString();
    }

    @RequestMapping(value = "/feign-consumer3",method = RequestMethod.GET)
    public String feignConsummer3(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(refactorHelloService.hello("lujunfa")).append("\n")
                .append(refactorHelloService.hello("lujunchao",17)).append("\n")
                .append(refactorHelloService.hello(new org.junfalu.api.Entity.User("liangdongxiu",41,'f'))).append("\n");
        return stringBuilder.toString();
    }
}
