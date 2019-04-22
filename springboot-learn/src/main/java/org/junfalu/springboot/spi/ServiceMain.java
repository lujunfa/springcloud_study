package org.junfalu.springboot.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author: lujunfa  2019/4/22 17:31
 */
public class ServiceMain {
    public static void main(String[] args) {
        ServiceLoader<DubboService> dubboServices = ServiceLoader.load(DubboService.class);
        Iterator<DubboService> iterator = dubboServices.iterator();
        while(iterator.hasNext()){
            DubboService dubboService = iterator.next();
            dubboService.sayHello();
        }
    }
}
