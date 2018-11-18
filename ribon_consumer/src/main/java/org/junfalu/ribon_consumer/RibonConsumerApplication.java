package org.junfalu.ribon_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


//开启eureka客户端
@EnableDiscoveryClient
@SpringBootApplication

//开启断路器功能
@EnableCircuitBreaker
//该注解为组合注解，组合了上面三个注解，所以该注解等同于上面三个注解
//@SpringCloudApplication
public class RibonConsumerApplication {

    @Bean
    @LoadBalanced//负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();     //rest访问对象
    }

    public static void main(String[] args) {
        SpringApplication.run(RibonConsumerApplication.class, args);
    }
}
