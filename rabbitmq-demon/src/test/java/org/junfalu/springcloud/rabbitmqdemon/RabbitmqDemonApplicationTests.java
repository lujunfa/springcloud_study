package org.junfalu.springcloud.rabbitmqdemon;

import org.junfalu.springcloud.rabbitmqdemon.rabbitMq.RabbitConfigration;
import org.junfalu.springcloud.rabbitmqdemon.rabbitMq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = RabbitConfigration.class)
@SpringBootTest
public class RabbitmqDemonApplicationTests {

    @Autowired
    Sender sender;
    @Test
    public void contextLoads() {
        sender.send();
    }

}
