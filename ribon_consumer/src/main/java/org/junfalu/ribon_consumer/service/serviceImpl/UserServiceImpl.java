package org.junfalu.ribon_consumer.service.serviceImpl;

import com.netflix.discovery.util.StringUtil;
import org.junfalu.ribon_consumer.Entity.User;
import org.junfalu.ribon_consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: lujunfa
 * @Date: 2018/11/26 11:00
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RestTemplate restTemplate;
    @Override
    public User find(String name) {
        return restTemplate.getForObject("http://HELLO-SERVICE/getUser?name={1}",User.class,name);
    }

    @Override
    public List<User> findById(List<String> userIds) {
        return restTemplate.getForObject("http://HELLO-SERVICE/getUsers/{1}",List.class,StringUtil.join(","));
    }
}
