package org.junfalu.springcloud.rabbitmqdemon.rabbitMq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: lujunfa  2018/12/3 15:57
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    @RabbitHandler
    public void process(String hello){
        System.out.println("receiver :"+hello);
    }
}
