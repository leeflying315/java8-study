package com.lifei.designPattern.observer;

import java.util.concurrent.TimeUnit;

/**
 * @Author lifei
 * @Date 2021/8/1
 * @Description:
 */
public class ObservableTest {
    public static void main(String[] args) {
        Observable observableThread = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish done");
            return null;
        });
        observableThread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(observableThread.getCycle());

        final TaskLifecycle<String> lifecycle = new TaskLifecycle.EmptyLifecycle<String>() {
            public void onFinish(Thread thread, String result) {
                System.out.println("result is " + result);
            }
        };
        Observable observable = new ObservableThread<>(lifecycle, () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish done result");
            return "hello observer";
        });
        observable.start();
    }
}
