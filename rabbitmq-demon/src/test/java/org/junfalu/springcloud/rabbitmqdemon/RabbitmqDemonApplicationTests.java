package org.junfalu.springcloud.rabbitmqdemon;

import org.junfalu.springcloud.rabbitmqdemon.rabbitMq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RabbitmqDemonApplication.class)
public class RabbitmqDemonApplicationTests {

    @Autowired
    Sender sender;
    @Test
    public void contextLoads() {
        sender.send();
    }

}
