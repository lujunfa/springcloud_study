package org.junfalu.ribon_consumer;

import com.google.common.collect.Maps;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: lujunfa
 * @Date: 2018/11/29 17:41
 * @Description:
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {
        //hashMap迭代顺序和插入的顺序不一致
        Map<String,String> MAP = Maps.newHashMap();
        MAP.put("lujunfa","man");
        MAP.put("nixi","female");
        MAP.put("lujunchao","man");
        MAP.forEach((K,V)->{
            System.out.println("key "+K+" value "+V);
        });
        System.out.println();

        //因为LinkedHashMap是基于双向链表，所以迭代顺序与插入顺序一致
        Map<String,String> linkedHashMap = Maps.newLinkedHashMap();
        linkedHashMap.put("lujunfa","man");
        linkedHashMap.put("nixi","female");
        linkedHashMap.put("lujunchao","man");
        linkedHashMap.forEach((K,V)->{
            System.out.println("key "+K+" value "+V);
        });
        System.out.println();

        //复写removeEldestEntry方法，使得当添加Entry时，可根据该方法返回的结果将最前面的数据进行剔除
        //这里的true参数，代表按访问顺序进行排序，即最近访问的元素放到最后面，基于这俩个特性可以实现LRUCache即最近使用最频繁缓存
        Map<String,String> linkedHashMap2 = new LinkedHashMap<String,String>(16,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return this.size()>3;
            }
        };
        linkedHashMap2.put("lujunfa","man");
        linkedHashMap2.put("nixi","female");
        linkedHashMap2.put("lujunchao","man");
        System.out.println();
        linkedHashMap2.get("lujunfa");
        linkedHashMap2.forEach((K,V)->{
            System.out.println("key "+K+" value "+V);
        });
        System.out.println();
        linkedHashMap2.put("ludesheng","m");
        linkedHashMap2.forEach((K,V)->{
            System.out.println("key "+K+" value "+V);
        });
    }
}
