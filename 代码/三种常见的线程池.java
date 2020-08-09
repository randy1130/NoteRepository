package com.zr.test;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 三种常见的线程池，底层都是ThreadPoolExecutor类，里面有构造参数
 * 一个阻塞队列
 */
public class Demo {
    public static void main(String[] args) {
        /**
         *  指定容量的线程池
         *  ExecutorService pool = Executors.newFixedThreadPool(5);
         *  一池一线程池
         *   ExecutorService pool = Executors.newSingleThreadExecutor();
         *  自适应数目线程池
         *  ExecutorService pool = Executors.newCachedThreadPool();
         *
         */
        ExecutorService pool = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i <= 10; i++) {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "线程执行了");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }

    }
}