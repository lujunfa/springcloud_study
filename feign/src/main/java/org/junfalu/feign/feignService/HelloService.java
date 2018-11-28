package org.junfalu.feign.feignService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: lujunfa
 * @Date: 2018/11/27 20:34
 * @Description:定义HelloService接口，通过@FeignClient注解指定服务名来绑定服务，然后在使用Spring MVC的注解
 * 来绑定具体该服务提供的Rest接口
 */

@FeignClient("hello-service") //这里服务名是不区分大小写的，即也可以为HELLO-SERVICE
public interface HelloService {

    @RequestMapping("/hello")
    String hello();
}

