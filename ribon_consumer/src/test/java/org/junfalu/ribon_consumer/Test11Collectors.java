package org.junfalu.ribon_consumer;

import com.google.common.collect.Maps;

import java.util.*;
import java.util.stream.Collectors;

class User {
    private String name ;

    private int score ;

    private int age;

    public User(String name, int score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name+" "+ age+" "+ score;
    }
}
public class Test11Collectors {
    public static void main(String[] args) {
        User user1 = new User("zhangsan", 60, 20);
        User user2 = new User("lisi", 80, 23);
        User user3 = new User("zhangsan", 80, 24);
        User user4 = new User("wangwu", 50, 24);
        User user5 = new User("wangwu2", 50, 24);
 
        List<User> userList = Arrays.asList(user1, user2, user3, user4, user5);
 
        //算出分数最小的那个并输出
        userList.stream().collect(Collectors.minBy(Comparator.comparingInt(User::getScore))).ifPresent(System.out::println);
 
        //算出分数最大的那个并输出（无法做到多个并列的时候求值）
        Optional optional = userList.stream().collect(Collectors.maxBy(Comparator.comparingInt(User::getScore)));

        Optional optional1 = userList.stream().max((o1,o2)->{
          return Integer.compare(o1.getScore(),o2.getScore());
        });
        System.out.println("option:"+optional.get()+" option2:"+optional1.get());
        //optional.isPresent(System.out::println);//isPresent是判断是否存在，不能接受参数
        optional.ifPresent(System.out::println);//直接使用时ifPresent
 
        //算出分数平均值并输出
        double averagint = userList.stream().collect(Collectors.averagingInt(User::getScore));
        System.out.println("averagint = " + averagint);
 
        //算出分数总和并输出
        int summingInt = userList.stream().collect(Collectors.summingInt(User::getScore));
        System.out.println("summingInt = " + summingInt);
 
        //算出汇总信息
        IntSummaryStatistics intSummaryStatistics = userList.stream().collect(Collectors.summarizingInt(User::getScore));
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);
 
        //拼接名字
        String nameStrs = userList.stream().map(User::getName).collect(Collectors.joining(", "));
        System.out.println("nameStrs = " + nameStrs);
 
        //拼接名字，调用另外一个方法，可以加前缀和后缀
        String nameStrs2 = userList.stream().map(User::getName).collect(Collectors.joining(", ", "[", "]"));
        System.out.println("nameStrs2 = " + nameStrs2);
 
        //分组：按照分数（返回的map的key是根据分组的条件来决定的，score是int，那么key就是Integer）
        Map<Integer, List<User>> scoreUsers = userList.stream().collect(Collectors.groupingBy(User::getScore));
        System.out.println("scoreUsers = " + scoreUsers);
 
        //二级分组：线按照分数分组，返回一个Map<Integer, List<User>>, 在根据用户名分组
        Map<Integer, Map<String, List<User>>> scoreNameUsers = userList.stream().collect(Collectors.groupingBy(User::getScore, Collectors.groupingBy(User::getName)));
        System.out.println("scoreNameUsers = " + scoreNameUsers);

        //根据分数分组，统计每个分数的年龄
        Map<Integer, Set<Integer>> scoreUserSet = userList.stream().collect(Collectors.groupingBy(User::getScore, Collectors.mapping(User::getAge,Collectors.toSet())));
        System.out.println("scoreUserSet = " + scoreUserSet);
 
        //分区,是否及格
        Map<Boolean, List<User>> jigeUsers = userList.stream().collect(Collectors.partitioningBy(user -> user.getScore() >= 60));
        System.out.println("jigeUsers = " + jigeUsers);
 
        //二级分区,是否及格,及格里面是否大于80
        Map<Boolean, Map<Boolean, List<User>>> youxiuUsers = userList.stream().collect(Collectors.partitioningBy(user -> user.getScore() >= 60, Collectors.partitioningBy(user -> user.getScore() >= 80)));
        System.out.println("youxiuUsers = " + youxiuUsers);
 
        //分区,是否及格,算出及格的个数
        Map<Boolean, Long> jigeUserCount = userList.stream().collect(Collectors.partitioningBy(user -> user.getScore() >= 60, Collectors.counting()));
        System.out.println("jigeUserCount = " + jigeUserCount);

        //计算分数大于等于60的人数
        Long count= userList.stream().filter(user -> user.getScore()>= 60).count();
        System.out.println("及格人数"+count);
 
        //先按照名字分组,获取每个分组分数最小的
        Map<String, User> UserCount = userList.stream().collect(Collectors.groupingBy(User::getName, Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt(User::getScore)), Optional::get)));
        System.out.println("UserCount = " + UserCount);

        //获取分数最高的前俩个人信息
        System.out.println("获取分数最高的前俩个人信息");
        userList.stream().sorted((o1,o2)->Integer.compare(o1.getScore(), o2.getScore()))
                .limit(2).collect(Collectors.toList()).forEach(System.out::println);


        //降序排序
        System.out.println("====降序排序");
        userList.stream().sorted(Comparator.comparing(User::getAge).reversed())
                    .collect(Collectors.toList()).forEach(System.out::println);

        //升序排序
        System.out.println("====升序排序");
        userList.stream().sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList()).forEach(System.out::println);

        //求平均数
        System.out.println("===平均分数");
        OptionalDouble optionalDouble =  userList.stream().filter(user-> user.getScore()>=60).mapToInt(user->user.getScore()).average();
        System.out.println("平均分数："+optionalDouble.getAsDouble());

        //所有的满足条件
        System.out.println("所有人分数都大于60:"+userList.stream().allMatch(user-> user.getScore()>60));

        //多字段进行排序
        System.out.println("====多字段参与排序");
        Comparator<User> comparator = Comparator.comparing(User::getAge,(o1,o2)->{
            return o1.compareTo(o2);
        }).thenComparing(User::getScore,(o1,o2)->{
            return o1.compareTo(o2);
        });
        userList.stream().sorted(comparator).forEach(System.out::println);


        //userMap : key->name value->user
        //这个如果key有重复的话会报duplicatekey 错误，因为存在相同名字的user，为了避免这个错误，这里用tomap的重载方法，意思当
        //t1和t2一致的话，t2覆盖t1,你也可以自己编写其他合并策略
        // Map<String, User> userMap = userList.stream().collect(Collectors.toMap(User::getName, Function.identity());
        //Function.identity() 等同于 t1->t1
        Map<String, User> userMap = userList.stream().collect(Collectors.toMap(User::getName, t1->t1, (t1,t2) ->t1));
        System.out.println("userMap= "+ userMap);

        Map<String,String> map = Maps.newHashMap();
        map.put("lujunfa", "patric");
        map.put("nixi", "linna");
        map.forEach((k,v)->{
            if(k.equals("lujunfa")){
                return;
            }
            System.out.println(v);
        });
 
    }
}
