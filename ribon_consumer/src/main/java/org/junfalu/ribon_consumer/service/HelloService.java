package org.junfalu.ribon_consumer.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    //hystrix断路器，当远端服务出现问题时，可以调用回调方法进行处理，另外hystrix的默认超时时间是2000毫秒，所以超过这个时间也会触发断路器
    @HystrixCommand(fallbackMethod = "helloFallBack")  //默认是同步执行
    public String helloService(){
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }

    @HystrixCommand(fallbackMethod = "helloFallBack")  //异步执行
    public Future<String> helloServiceSync(){
        return  new com.netflix.hystrix.contrib.javanica.command.AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
            }
        };
    }

    public String helloFallBack(){
        return "error";
    }

}
