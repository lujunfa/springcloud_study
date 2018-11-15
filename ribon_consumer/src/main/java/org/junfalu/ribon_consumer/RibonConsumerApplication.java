package org.junfalu.ribon_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
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
