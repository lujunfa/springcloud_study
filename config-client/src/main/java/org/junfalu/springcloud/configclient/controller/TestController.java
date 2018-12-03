package org.junfalu.springcloud.configclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController

public class TestController {

    @Value("${from}")
    private String from;

    @Autowired
    private Environment environment;

    @RequestMapping("/from")
    public String from(){
        return from;
    }
}

