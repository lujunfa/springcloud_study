package org.junfalu.api.Entity;

/**
 * @Author: lujunfa
 * @Date: 2018/11/16 13:56
 * @Description:
 */
public class User {
    private  String name ;

    private Integer age;

    private char gender;

    public User() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public User(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

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
