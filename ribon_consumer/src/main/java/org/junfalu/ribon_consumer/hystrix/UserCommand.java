package org.junfalu.ribon_consumer.hystrix;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
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
        /**
         * 。通过设置命令组来组织和统计命令的告警，仪表盘信息，另外，Hystrix命令默认的线程划分也是根据命令分组来的实现的，
         * 默认下，Hystrix会让相同组名的命令使用同一个线程池，所以我们需要在创建Hystrix命令时为其指定组名来实现默认的线程
         * 划分
         */
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))    //命令分组  必须的
                .andCommandKey(HystrixCommandKey.Factory.asKey("UserCommand"))              //设置命令名 非必须的
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolKey"))      //设置线程划分，相对于命令分组实现线程划分，粒度更小
        );

        this.restTemplate = restTemplate;
        this.name = name;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForEntity("http://HELLO-SERVICE/getUser?name={1}",User.class, name).getBody();
    }


    //定义服务降级
    @Override
    protected User getFallback() {
        //当命令失败时触发服务降级，可以通过getExecutionException方法得到异常对象，然后针对异常进行处理
        Throwable throwable = this.getExecutionException();

        return new User();
    }

    /**
     * hystrix 中使用命令缓存缓解并发压力
     */
    @Override
    protected String getCacheKey() {
        return name;
    }


    //当有写操作时，可调用该方法进行读操作的缓存清理
    public static void flushCache(String name){
        HystrixRequestCache.getInstance(HystrixCommandKey.Factory.asKey("UserCommand"),
                HystrixConcurrencyStrategyDefault.getInstance()).clear(name);
    }
}
