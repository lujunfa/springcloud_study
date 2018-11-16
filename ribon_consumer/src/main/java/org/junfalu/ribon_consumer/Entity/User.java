package org.junfalu.ribon_consumer.Entity;

/**
 * @Author: lujunfa
 * @Date: 2018/11/16 13:56
 * @Description:
 */
public class User {
    private  String name ;

    private int age;

    private char gender;

    public String getName() {
        return name;
    }

    public static User init(){
        User user = new User();
        user.age=24;
        user.name = "lujunfa";
        user.gender='m';
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }
}
