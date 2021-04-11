package com.lifei.study.threadstudy.waitnotify;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

/**
 * @Author: lifei
 * @Date: 2021/1/5 11:33
 * @Description: 消息队列
 */

public class EventQueue {

    private final int max;

    static class Event{

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue(){
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max){
        this.max = max;
    }

    public void offer(Event event){
        synchronized (eventQueue){
            if(eventQueue.size()>=max){
                try {
                    console("the queue is full");
                    // wait 会持续等待
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console("the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take(){
        synchronized (eventQueue){
            if(eventQueue.isEmpty()){
                try {
                    console("the queue is empty");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            console("the event " + event + "is handled.");
            return event;
        }
    }

    private void console(String message){
        System.out.printf("%s:%s\n",currentThread().getName(),message);
    }
}
