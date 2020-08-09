package com.zr.test;

import java.util.concurrent.TimeUnit;

/**
 * 死锁的现象
 */
class MyRunable implements Runnable {
    private String data1;
    private String data2;

    public MyRunable(String data1, String data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    @Override
    public void run() {
        synchronized (data1) {
            System.out.println(Thread.currentThread().getName() + "线程持有" + data1 + "尝试获得" + data2);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (data2) {
                System.out.println(Thread.currentThread().getName() + "线程持有所有锁");
            }
        }
    }
}

public class Demo {
    public static void main(String[] args) {
        String data1 = "a";
        String data2 = "b";
        new Thread(new MyRunable(data1, data2), "Thread A").start();
        new Thread(new MyRunable(data2, data1), "Thread B").start();
    }
}