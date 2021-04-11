package com.lifei.study.threadstudy.waitnotify;

import java.util.concurrent.TimeUnit;

/**
 * @Author: lifei
 * @Date: 2021/1/5 11:28
 * @Description: 线程 wait notify 用法示例
 */

public class EventClient {
    public static void main(String[] args) {
        final  EventQueue eventQueue = new EventQueue();
        new Thread(()->{
           for(;;){
               eventQueue.offer(new EventQueue.Event());
           }
        },"Producer").start();

        new Thread(()->{
            for(;;){
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"consumer").start();
    }
}
