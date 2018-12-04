package org.junfalu.springcloud.configclient;

import org.junfalu.springcloud.configclient.RandomServerPort.MyApplicationEnvironmentPreparedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ConfigClientApplication.class);
        app.addListeners(new MyApplicationEnvironmentPreparedEventListener());
        app.run(args);
    }
}
