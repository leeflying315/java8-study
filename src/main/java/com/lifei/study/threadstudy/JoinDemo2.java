package com.lifei.study.threadstudy;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @Author: lifei
 * @Date: 2020/12/30 14:41
 * @Description: JoinDemo实验2
 */

public class JoinDemo2 {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = IntStream.range(1,3).mapToObj(JoinDemo2::create).collect(toList());

        threadList.forEach(Thread::start);
        for(Thread thread: threadList) {
            // join 会使得当前线程等待
            thread.join();
        }

        for(int i = 0 ;i < 10;i++){
            System.out.println(Thread.currentThread().getName()+"#"+i);
            shortSleep();
        }
    }

    private static Thread create(int seq){
        return new Thread(()->{
           for(int i=0;i<10;i++){
               System.out.println(Thread.currentThread().getName()+"#"+i);
               shortSleep();
           }

        },String.valueOf(seq));
    }

    private static void shortSleep(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
