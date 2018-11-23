package org.junfalu.ribon_consumer.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.junfalu.ribon_consumer.Entity.User;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: lujunfa
 * @Date: 2018/11/23 16:31
 * @Description:封装具体的依赖服务调用逻辑
 */
public class UserCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;

    private String name ;

    public UserCommand(RestTemplate restTemplate, String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.restTemplate = restTemplate;
        this.name = name;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForEntity("http://HELLO-SERVICE/getUser?name={1}",User.class, name).getBody();
    }
}
