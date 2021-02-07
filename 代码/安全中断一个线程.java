package com.sxt;

/**
 * 安全中断一个线程
 */
public class Main {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(5 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runnable.stop = false;
        System.out.println("main 线程结束！");
    }
}

class MyRunnable implements Runnable {

    boolean stop = true;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (stop) {
                System.out.println(Thread.currentThread().getName() + "----->" + i);
                try {
                    Thread.sleep(2 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // 保存数据
                return;
            }
        }

    }
}