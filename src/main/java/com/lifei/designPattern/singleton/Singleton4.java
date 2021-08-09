package com.lifei.designPattern.singleton;

import java.net.Socket;
import java.sql.Connection;

/**
 * @Author lifei
 * @Date 2021/7/30
 * @Description:
 * Double-check 模式
 * 根据Happens Before原则和指令重排序， 这三者实例化的顺序并无前后关系约束，有可能instance先被实例化，
 * conn socket并未完成实例化。未完成初始化的实例此时被调用将会空指针异常
 */
public final class Singleton4 {
    private static Singleton4 instance = null;
    Connection conn;
    Socket socket;

    private Singleton4(){
        // 初始化
        // this.socket = new ...
        // this.conn = new ...
    }
    // dan
    public static Singleton4 getInstance() {
        // 当两个线程同时判断instance为null时，都进入下方
        if (null == instance) {
            // 只有一个线程能够获得Singleton.class关联的monitor
            // 另一个线程在等待
            synchronized (Singleton4.class) {
                // 如果instance 为null 则创建
                // 创建完成后释放锁，下一个等待的线程判断，此时实例已创建完成
                if (null == instance) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
