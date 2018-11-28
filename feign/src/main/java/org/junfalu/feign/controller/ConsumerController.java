package org.junfalu.feign.controller;

import org.junfalu.feign.feignService.HelloService;
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

    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String feignConsummer(){
        return helloService.hello();
    }
}
