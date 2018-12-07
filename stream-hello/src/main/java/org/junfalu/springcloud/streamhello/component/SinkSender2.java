package org.junfalu.springcloud.streamhello.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: lujunfa  2018/12/7 14:43
 * 原生消息生产者
 */

@EnableBinding(value ={Sink.class} )
public class SinkSender2 {
    private static final Logger LOGGER  = LoggerFactory.getLogger(SinkSender2.class);

    @Bean
    @InboundChannelAdapter(value = SinkOutPut.OUTPUT,poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Date> timerMessageSource(){
        return  () -> new GenericMessage<Date>(new Date());
    }

    public interface SinkOutPut{
        String OUTPUT="output";

        @Output(SinkOutPut.OUTPUT)
        MessageChannel output();
    }

    /**
     * 指定消息通道的消息转化
     * @param message
     * @return
     */
    @Transformer(inputChannel = Sink.INPUT, outputChannel = Sink.INPUT)
    public Object transform(Date message){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message);
    }
}
