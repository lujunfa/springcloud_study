package org.junfalu.springcloud.rabbitmqdemon.rabbitMq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lujunfa  2018/12/3 15:58
 */
@Configuration
public class RabbitConfigration {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

}
