package org.junfalu.ribon_consumer.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.junfalu.ribon_consumer.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    //hystrix断路器，当远端服务出现问题时，可以调用回调方法进行处理，另外hystrix的默认超时时间是2000毫秒，所以超过这个时间也会触发断路器
    @HystrixCommand(fallbackMethod = "helloFallBack")  //默认是同步执行    hystrixCommand
    public String helloService(){
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }

    @HystrixCommand(fallbackMethod = "helloFallBack")  //异步执行           hystrixCommand
    public Future<String> helloServiceSync(){
        return  new com.netflix.hystrix.contrib.javanica.command.AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
            }
        };
    }

    //observableExecutionMode 表示执行方式是obersve()方式，即返回的是热observable对象
    //ignoreExceptions 表示忽略指定异常，不触发服务降级
    //使用注解实现请求缓存:
    //@CacheResult标记请求命令返回的结果应该被缓存，必须与HystrixCommand注解结合使用
    //@CacheRemove该注解用来让请求命令缓存失效
    //@CacheKey该注解用在请求命令的参数标记上，使其作为缓存的key值，如果没有标注则会使用所有参数
    @CacheResult
    @HystrixCommand(fallbackMethod = "helloFallBack", observableExecutionMode = ObservableExecutionMode.EAGER
            , ignoreExceptions = HystrixBadRequestException.class, commandKey = "", groupKey = "", threadPoolKey = "")  // @HystrixCommand注解定义hystrixobservablecommand命令    hystrixobservablecommand
    public Observable<String> getHelloHystrixObservableCommand(){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try{
                    String hello = restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
                    subscriber.onNext(hello);
                    subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        });
    }

    //通过@hystrixCommand申明的命令服务降级时，对应的降级方法只要放入异常形参就可以接收到异常对象
    public String helloFallBack(Throwable throwable){
        return "error";
    }

}
