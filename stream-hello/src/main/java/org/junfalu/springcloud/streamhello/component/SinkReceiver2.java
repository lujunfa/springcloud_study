package org.junfalu.springcloud.streamhello.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: lujunfa  2018/12/7 14:28
 * 采用原生Integration的消息驱动服务机制
 */

@EnableBinding(value = {Sink.class})
public class SinkReceiver2 {

    private static final  Logger LOGGER = LoggerFactory.getLogger(SinkReceiver2.class);

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void receiver(Object payLoad){
        LOGGER.info("received:" + payLoad);
    }

    /**
     * 当json消息到来时，可以通过@Transform将其转换为对应的对象然后提供给消费者消费
     * @param message
     * @return
     */
    @Transformer(inputChannel = Sink.INPUT, outputChannel = Sink.INPUT)
    public Object transform(Date message){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message);
    }
}
