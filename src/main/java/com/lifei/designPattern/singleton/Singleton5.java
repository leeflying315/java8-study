package com.lifei.designPattern.singleton;

import java.net.Socket;
import java.sql.Connection;

/**
 * @Author lifei
 * @Date 2021/7/30
 * @Description:
 * Double check + violate
 */
public final class Singleton5 {
    // 禁止指令重排
    private volatile static Singleton5 instance = null;
    Connection conn;
    Socket socket;

    private Singleton5(){
        // 初始化
        // this.socket = new ...
        // this.conn = new ...
    }
    // dan
    public static Singleton5 getInstance() {
        // 当两个线程同时判断instance为null时，都进入下方
        if (null == instance) {
            // 只有一个线程能够获得Singleton.class关联的monitor
            // 另一个线程在等待
            synchronized (Singleton4.class) {
                // 如果instance 为null 则创建
                // 创建完成后释放锁，下一个等待的线程判断，此时实例已创建完成
                if (null == instance) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
