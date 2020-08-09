package com.zr.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


/**
 * 同步队列
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t 线程插入了1");
                /**
                 * 插入一个数据后，不取出来，这个线程不会再插入数据等到取出来后再插入下条数据
                 */
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "\t 线程插入了2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t 线程插入了3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t 线程取出了" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t 线程取出了" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t 线程取出了" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

    }
}