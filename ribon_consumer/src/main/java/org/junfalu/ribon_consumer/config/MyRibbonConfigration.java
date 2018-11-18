package org.junfalu.ribon_consumer.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ribbon定制化配置
 */
@Configuration
public class MyRibbonConfigration {

    /**
     * 定制化ribbon的Ping组件
     * @param clientConfig
     * @return
     */
   /* @Bean
    public IPing ribbonPing(IClientConfig clientConfig){
        return new PingUrl();
    }*/
}
