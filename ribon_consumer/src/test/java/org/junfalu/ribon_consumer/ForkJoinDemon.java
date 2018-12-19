package org.junfalu.ribon_consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * @Author: lujunfa  2018/12/19 15:44
 * fork/join
 */
public class ForkJoinDemon {

    public class JoinTask extends RecursiveTask<List<String>>{
        private JoinTask joinTask;
        private List<String> lists ;
        private  int threadhold ;

        public JoinTask(List<String> lists , int threadhold){
            this.lists = lists;
            this.threadhold = threadhold;
        }

        @Override
        protected List<String> compute() {
              if(lists.size() < threadhold){
                  return lists.stream().filter(v->v.contains("a")).collect(Collectors.toList());
              }else{
                  int middle = lists.size()/2;
                  List<String> rightList = lists.subList(0,middle);
                  List<String> leftList = lists.subList(middle, lists.size());
                  JoinTask right = new JoinTask(rightList,threadhold);
                  JoinTask left = new JoinTask(leftList, threadhold);

                  //并行执行俩个子任务
                  left.fork();
                  right.fork();

                  //把俩个子任务的结果集合起来
                  List<String> result = left.join();
                  result.addAll(right.join());
                  return result;
              }
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("lujunfa","sdfs","ssdwe","uy","ll",
                "bb","tr","sdfs","ssdwe","lujunfa","sdfs","ssdwe",
                "qq","hj","sdfs","ssdwe","lujunfa","sdfs","ssdwe",
                "dsdd","kj","n","ssdwe","lujunfa","sdfs","ssdwe",
                "hgh","ll","arer","ssdwe","h","sdfs","ssdwe",
                "aa","jh","kjk","ssdwe","lujunfa","sdfs","ssdwe",
                "d","vc","hhj","ssdwe","nm","sdfs","ssdwe",
                "sd","gg","bb","ssdwe","lujunfa","sdfs","ssdwe");
        List<String> filterStr = new ArrayList<>();

        for(int i=0; i<100000; i++){
            filterStr.addAll(list);
        }

        ForkJoinDemon forkJoinDemon = new ForkJoinDemon();
        //改变theadhold值，消耗时间不一样
        ForkJoinDemon.JoinTask joinTask = forkJoinDemon.new JoinTask(filterStr, 58);

        long begin = System.currentTimeMillis();
        List<String> strs1 = joinTask.compute();
        long end = System.currentTimeMillis();
        System.out.println("总共耗时:"+(end-begin));

         begin = System.currentTimeMillis();
         List<String> str2 = new ArrayList<>();
         for(int i=0; i<filterStr.size(); i++){
             if(filterStr.get(i).contains("a"))
                 str2.add(filterStr.get(i));
         }
         end = System.currentTimeMillis();

        System.out.println("总共耗时:"+(end-begin));

    }
}
