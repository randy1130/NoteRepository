package com.sxt;

/**
 * 守护线程
 */
public class Main {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.setDaemon(true); // 设置为守护线程
        thread.start();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main线程执行" + (i) + "次!");
        }
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("守护线程执行" + (++i) + "次！");
        }
    }
}