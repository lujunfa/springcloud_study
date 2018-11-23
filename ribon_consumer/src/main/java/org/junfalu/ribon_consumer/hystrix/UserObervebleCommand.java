package org.junfalu.ribon_consumer.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.junfalu.ribon_consumer.Entity.User;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

/**
 * @Author: lujunfa
 * @Date: 2018/11/23 17:20
 * @Description:
 */
public class UserObervebleCommand extends HystrixObservableCommand<User> {
    private RestTemplate restTemplate;

    private String name;

    public UserObervebleCommand(RestTemplate restTemplate, String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup1"));
        this.restTemplate = restTemplate;
        this.name = name;
    }

    @Override
    protected Observable<User> construct() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try{
                    if(subscriber.isUnsubscribed()  ){
                        User user = restTemplate.getForEntity("http://HELLO-SERVICE/getUser?name={1}",User.class, name).getBody();
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }
                }catch (Exception e){
                        subscriber.onError(e);
                }
            }
        });
    }
}
