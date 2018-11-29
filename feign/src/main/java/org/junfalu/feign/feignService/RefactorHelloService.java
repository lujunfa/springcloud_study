package org.junfalu.feign.feignService;

import org.junfalu.api.service.HelloService;
import org.junfalu.feign.fallback.HelloServiceFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Author: lujunfa
 * @Date: 2018/11/29 11:02
 * @Description:
 */
@FeignClient(value = "hello-service", fallback = HelloServiceFallBack.class)
public interface RefactorHelloService extends HelloService {
}
