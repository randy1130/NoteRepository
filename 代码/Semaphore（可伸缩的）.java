package com.zr.test;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 作用：解决多个线程操作多个资源的问题
 * 可以预设一个数字值3，当来的请求为6个时，先进入3个，当其中有一个离开时
 * 在进去一个，最后直到全部完成。（类似于景点停车）
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(3);//模拟三个车位
        //有6个车需要车位
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();//有一个车占了一个车位
                    System.out.println(Thread.currentThread().getName() + "\t 抢到了车位");
                    int seconds = new Random().nextInt(5) + 1;
                    TimeUnit.SECONDS.sleep(seconds);//这个车待的一个随机的时间(1到6秒内的一个时间段)
                    System.out.println(Thread.currentThread().getName() + "\t 离开了车位，用来" + seconds + "秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//这个车走了离开了车位
                }
            }, String.valueOf(i)).start();
        }
    }
}