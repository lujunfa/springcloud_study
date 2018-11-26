package org.junfalu.ribon_consumer.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.junfalu.ribon_consumer.Entity.User;
import org.junfalu.ribon_consumer.service.UserService;

import java.util.List;

/**
 * 批量请求命令，这里用在请求合并中
 * @Author: lujunfa
 * @Date: 2018/11/26 11:07
 * @Description:
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {
    UserService userService;
    List<String> userIds ;
    public UserBatchCommand( UserService userService, List<String> userIds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userServiceCommand")));
        this.userService = userService;
        this.userIds = userIds;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.findById(userIds);
    }
}
