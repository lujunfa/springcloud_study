package org.junfalu.springboot.spi;

/**
 * @Author: lujunfa  2019/4/22 17:25
 */
public class RedDubboServiceImpl implements DubboService {
    @Override
    public void sayHello() {
        System.out.println("hi i am red");
    }
}
