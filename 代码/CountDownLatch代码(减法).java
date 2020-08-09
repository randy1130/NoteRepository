package com.zr.test;

import java.util.concurrent.CountDownLatch;

/**
 * 目的是让一个或者多个线程等待，直到其他线程的一系列操作完成。
 * 保证其他线程完全结束操作，主（main）线程才执行。
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        /**
         * CountDownLatch是具有synchronized机制的一个工具，
         * 计数预设值为6个。
         */
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "线程  执行完了");
                //当一个线程执行完了上述的代码，就把计数值减1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //main线程在此等待，直到计数值为0的时候才能执行以下的代码
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "#####线程  执行完了");
    }
}