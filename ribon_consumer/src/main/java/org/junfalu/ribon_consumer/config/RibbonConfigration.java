package org.junfalu.ribon_consumer.config;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;


/**
 * 通过@RibbonClient注解实现更细粒度的客户端配置
 */
@Configuration
@RibbonClient(name = "hello-service",configuration = MyRibbonConfigration.class ) //指定对应配置类
public class RibbonConfigration {
}
