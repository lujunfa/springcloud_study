package org.junfalu.ribon_consumer.controller;

import org.junfalu.ribon_consumer.Entity.User;
import org.junfalu.ribon_consumer.hystrix.UserCommand;
import org.junfalu.ribon_consumer.hystrix.UserObervebleCommand;
import org.junfalu.ribon_consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Auther: lujunfa
 * @Date: 2018/11/15 14:17
 * @Description:服务消费
 */

/**
 * eureka也会将服务消费方当做服务注册进注册中心
 */
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/ribbon-consummer",method = RequestMethod.GET)
    public String helloConsumer(){
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public User getUser(@RequestParam("name") String name){
  /*      Map<String,String> map = new HashMap();
        map.put("name", name);
        return restTemplate.getForEntity("http://HELLO-SERVICE/getUser?name={name}",User.class, map).getBody();

        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://HELLO-SERVICE/getUser?name={name}")
                .build()
                .expand("lujunfa")
                .encode();
        URI uri = uriComponents.toUri();
        return restTemplate.getForEntity(uri,User.class).getBody();*/

        return restTemplate.getForEntity("http://HELLO-SERVICE/getUser?name={1}",User.class, name).getBody();
    }

    @RequestMapping(value = "/hystrix-helloservice")
    public String hystrixHelloService(){
        return helloService.helloService();
    }

    /**
     * 功能描述: 异步调用
     * @return: java.lang.String
     * @date: 2018/11/23 17:10
     */
    @RequestMapping(value = "/hystrixAysnc")
    public String hystrixAysnc() throws ExecutionException, InterruptedException {
        return helloService.helloServiceSync().get();
    }

    @RequestMapping("/hysstrix-command")
    public User getUserHystrixCommand(@RequestParam("name") String name){
        UserCommand userCommand = new UserCommand(restTemplate,name);
        User user = userCommand.execute();//同步执行
       /* Future<User> future = userCommand.queue();//异步执行
        Observable<User> hot =  userCommand.observe();  //响应式执行
        Observable<User> cold = userCommand.toObservable(); //响应式执行*/
        return user;
    }

    @RequestMapping("/hystrixObservableCommand")
    public User hystrixObservableCommand(@RequestParam("name") String name){
        UserObervebleCommand command  = new UserObervebleCommand(restTemplate,name);
        Observable<User> userObservable = command.toObservable();
        return  userObservable.
    }
}
