package org.junfalu.springcloud.streamhello.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @Author: lujunfa  2018/12/5 16:00
 */

/**
 * 该注解用来指定一个或多个定义了@Input或@Output注解的接口，以此实现对消息通道Channnel的绑定，这里绑定了Sink接口，该接口
 * 是Spring cloud stream默认实现的对输入消息通道绑定的定义
 */
@EnableBinding(value = {Sink.class,SinkSender.class }) //通道绑定，创建对应的生产，消费实例
public class SinkReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SinkReceiver.class);


    /**
     * 将被修饰的方法，注册为消息中间件上数据流的事件监听器，注解中的属性值对应了监听的消息通道名
     * @param payLoad
     */
    @StreamListener(Sink.INPUT)
    public void receive(Object payLoad){
        LOGGER.info("RECEIVER:"+ payLoad);
    }
}
