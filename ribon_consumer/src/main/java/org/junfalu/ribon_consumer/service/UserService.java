package org.junfalu.ribon_consumer.service;

import org.junfalu.ribon_consumer.Entity.User;

import java.util.List;

/**
 * @Author: lujunfa
 * @Date: 2018/11/26 10:59
 * @Description:
 */
public interface UserService {

    User find(String name);

    List<User> findById(List<String> usrIds);
}
