package org.junfalu.springcloud.streamhello.component;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

/**
 * @Author: lujunfa  2018/12/6 14:43
 */
public interface SinkSender {

    @Output(Sink.INPUT)
    MessageChannel output();
}
