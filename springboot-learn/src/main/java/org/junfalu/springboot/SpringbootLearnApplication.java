package org.junfalu.springboot;

import org.junfalu.springboot.RamdonServerPort.MyApplicationEnvironmentPreparedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//开启eureka客户端
@EnableDiscoveryClient
public class SpringbootLearnApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootLearnApplication.class);
        app.addListeners(new MyApplicationEnvironmentPreparedEventListener());
        app.run(args);
    }
}
