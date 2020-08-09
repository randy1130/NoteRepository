package com.zr.test;


import java.util.concurrent.atomic.AtomicReference;

public class Demo {
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t myLock方法执行了");
        /**
         * t1线程先到此处，执行完下面的代码，把线程对象放入到AtomicReference
         * 中，然后t2线程到达时，只能在下面的代码中死循环，等到t1执行完myUnLock()
         * 方法将AtomicReference里面的线程换为null，然后t1结束死循环，将自己放入到
         * AtomicReference中，走完下面的调用的方法
         */
        while (!atomicReference.compareAndSet(null, thread)) {
        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t myUnLock方法执行了");
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        new Thread(() -> {
            demo.myLock();
            /**
             * 线程在中间睡眠5秒，以保证t2线程到
             * 自旋锁处自旋
             */
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.myUnLock();
        }, "t1").start();
        new Thread(() -> {
            /**
             * 线程先睡眠1秒，以保证t1线程先走
             */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.myLock();
            demo.myUnLock();
        }, "t2").start();
    }
}