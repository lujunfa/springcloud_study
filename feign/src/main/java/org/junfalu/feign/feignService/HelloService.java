package org.junfalu.feign.feignService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: lujunfa
 * @Date: 2018/11/27 20:34
 * @Description:定义HelloService接口，
 */

@FeignClient("hello-service")
public interface HelloService {

    @RequestMapping("/hello")
    String hello();
}

