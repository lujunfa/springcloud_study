package org.junfalu.feign.feignService;

import org.junfalu.api.service.HelloService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Author: lujunfa
 * @Date: 2018/11/29 11:02
 * @Description:
 */
@FeignClient("hello-service")
public interface RefactorHelloService extends HelloService {
}
