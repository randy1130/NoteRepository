package com.zr.test;


public class Demo {
    public static void main(String[] args) {
        new Thread(new MyRunable(), "t1").start();
        new Thread(new MyRunable(), "t2").start();
    }
}

class MyRunable implements Runnable {
    @Override
    public void run() {
        method1();
    }

    public synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + "\t method1方法执行了！");
        /**
         * 在同步代码块中继续调用同步方法，可以运行。（证明synchronized为可重入锁）
         */
        method2();
    }

    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + "\t method2方法执行了！");
    }
}