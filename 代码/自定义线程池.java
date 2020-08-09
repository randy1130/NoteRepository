package com.zr.test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) throws Exception {
        /**
         * 自定义线程池
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2,//核心线程数
                5,//最大线程数
                1L,//空闲线程最大等待时间
                TimeUnit.MILLISECONDS,///空闲线程最大等待时间单位
                new LinkedBlockingQueue<Runnable>(3),//阻塞队列，参数为阻塞队列的容量
                Executors.defaultThreadFactory(),//默认的线程创建工厂
                /**
                 * 拒绝策略 AbortPolicy:抛出异常
                 *         CallerRunsPolicy:将任务返回给调用者（main线程）
                 *         DiscardOldestPolicy:抛弃等待最久的线程
                 *         DiscardPolicy:直接丢弃任务
                 */
                new ThreadPoolExecutor.DiscardPolicy());
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