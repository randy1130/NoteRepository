package com.zr.test;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "MyCallable执行了！");
        Thread.sleep(2000);//等待2秒，模拟新开的线程没有算完
        return 2;
    }
}

/**
 * Callable线程的使用
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new MyCallable());
        new Thread(integerFutureTask, "AAA").start();
        /**
         * 如果在开一个线程都用的是一个FutureTask那么call()方法只会执行一次
         *  例：new Thread(integerFutureTask, "BBB").start();
         */
        /**
         * 有可能上面的线程还没有计算完返回i，所以有可能导致mian线程阻塞，
         */
        Integer i = integerFutureTask.get();
        /**
         * 也可以在此处写一个自旋锁防止main线程阻塞，main线程会在此处死循环，
         * 但是没有意义，阻塞和死循环没区别
         */
        /*while (!integerFutureTask.isDone()) {
        }*/
        Integer j = 1;
        System.out.println(i + j);

    }
}