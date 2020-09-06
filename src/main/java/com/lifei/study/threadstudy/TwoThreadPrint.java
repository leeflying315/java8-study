package com.lifei.study.threadstudy;

// 两个线程交替打印
public class TwoThreadPrint implements Runnable
{

    private int i = 0;

    private String lock = "hello";

    public static void main(String[] args)
    {
        TwoThreadPrint twoThreadPrint = new TwoThreadPrint();
        Thread t1 = new Thread(twoThreadPrint);
        Thread t2 = new Thread(twoThreadPrint);
        Thread t3 = new Thread(twoThreadPrint);

        t1.start();
        t2.start();
        t3.start();
    }

    @Override
    public void run()
    {
        while (true)
        {
            synchronized (lock)
            {
                i++ ;
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {
                    System.out.println(e);
                }
//                lock.notifyAll();

                try
                {
                    lock.wait();
                }
                catch (InterruptedException e)
                {
                    System.out.println(e);
                }
            }
        }
    }
}
