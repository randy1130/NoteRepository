package com.zr.test;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Demo {
    public static void main(String[] args) throws Exception {
        //预设置一个数字和任务
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙！！！");
        });
        for (int i = 1; i <=7; i++) {
            final int j = i;
            new Thread(() -> {
                System.out.println("集齐了" + j + "龙珠");
                try {
                    //其他线程走到这里加1，等到加到预设值后，执行预设的方法
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}