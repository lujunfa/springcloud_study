package org.junfalu.ribon_consumer.hystrix.collapser;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesFactory;
import org.junfalu.ribon_consumer.Entity.User;
import org.junfalu.ribon_consumer.hystrix.command.UserBatchCommand;
import org.junfalu.ribon_consumer.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: lujunfa
 * @Date: 2018/11/26 11:12
 * @Description:请求合并器
 */
public class UserCollapserCommand extends HystrixCollapser<List<User>, User, String> {

    private UserService userService;
    private String userId;

    public UserCollapserCommand(UserService userService, String userId) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("userServiceCommand"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100))); //设置时间延长属性
        this.userService = userService;
        this.userId = userId;
    }

    @Override
    public String getRequestArgument() {
        return userId;
    }

    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, String>> collection) {
        List<String> userIds = new ArrayList<>(collection.size());
        userIds.addAll(collection.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new UserBatchCommand(userService, userIds);
    }

    @Override
    protected void mapResponseToRequests(List<User> batchResponse, Collection<CollapsedRequest<User, String>> collapsedRequests) {
        int count = 0;
        for(CollapsedRequest<User,String> collapsedRequest : collapsedRequests){
            User user = batchResponse.get(count++);
            collapsedRequest.setResponse(user);
        }
    }
}
