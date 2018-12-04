package org.junfalu.springcloud.rabbitmqdemon.rabbitMq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: lujunfa  2018/12/3 15:55
 */

@Component
public class Sender {
    @Autowired
    private AmqpTemplate template;

    public void send(){
        String context = "hello"+new Date();
        System.out.println("sender ："+context);
        this.template.convertAndSend("hello",context);
    }
}
