package org.junfalu.springboot.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: lujunfa
 * @Date: 2018/11/16 13:56
 * @Description:
 */
@ApiModel("用户实体")
public class User {

    @ApiModelProperty("名字")
    private  String name ;
    @ApiModelProperty("年龄")
    private int age;
    @ApiModelProperty("性别")
    private char gender;

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
