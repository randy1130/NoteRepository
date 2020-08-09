package com.test;

import java.util.concurrent.atomic.AtomicStampedReference;

public class Demo {
    /**
     * 构造方法中的第一个参数是数值，第二个是版本号
     */
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            /**
             * 先让t1拿到第一次的版本号
             */
            System.out.println(Thread.currentThread().getName() + "线程的第一次版本号" + atomicStampedReference.getStamp());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "线程的第二次版本号" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            /**
             * 改变后，值为原来的100了，但是版本号已经为3了
             */
            System.out.println(Thread.currentThread().getName() + "线程的第三次版本号" + atomicStampedReference.getStamp());
        }, "t1").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            /**
             * 拿到第一次的版本号，前面的t1线程等待一秒的作用就是这
             */
            System.out.println(Thread.currentThread().getName() + "线程的第一次版本号" + stamp);
            /**
             * 等待3秒钟，让t1线程跑完，把值改回来了为100，但是版本号为3了
             */
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**
             * 在此处的比较并交换的时候，原来的值是100比较到了，但是当前的版本号和原来的不一样了，所以无法写入实际的数据2019
             */
            atomicStampedReference.compareAndSet(100,2019,stamp,stamp+1);
            /**
             * 此处的值为100，版本号为3
             */
            System.out.println("值为="+atomicStampedReference.getReference()+"当前的版本号为="+atomicStampedReference.getStamp());
        }, "t2").start();
    }
}