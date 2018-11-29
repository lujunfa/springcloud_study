package org.junfalu.feign.feignService;

import org.junfalu.feign.Entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    /**
     *注意:在feign绑定参数中必须通过value属性来指定具体的参数名，不然会抛出IllegalStateException,value属性不能为空
     */
    @RequestMapping("/hello2")
    String hello2(@RequestParam("name") String name);

    @RequestMapping("/hello3")
    String hello3(@RequestHeader("name") String name, @RequestHeader("age") int age);

    @RequestMapping("/hello4")
    String hello4(@RequestBody User user);

}

