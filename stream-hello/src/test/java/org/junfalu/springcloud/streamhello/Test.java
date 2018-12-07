package org.junfalu.springcloud.streamhello;

import org.junfalu.springcloud.streamhello.component.SinkSender;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Author: lujunfa  2018/12/6 14:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StreamHelloApplication.class)
@WebAppConfiguration
public class Test {
    @Autowired
    private SinkSender sinkSender;

    @Qualifier("input")
    private MessageChannel messageChannel;

    @org.junit.Test
    public void contextLoads(){
        sinkSender.output().send(MessageBuilder.withPayload("from SinkSender").build());
    }

    @org.junit.Test
    public void contextLoads2(){
        messageChannel.send(MessageBuilder.withPayload("from SinkSender").build());
    }
}
