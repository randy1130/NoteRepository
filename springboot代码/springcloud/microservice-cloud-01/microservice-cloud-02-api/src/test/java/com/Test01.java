package com;


import java.util.concurrent.*;

public class Test01 {
    static volatile int j=1;
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                10L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory());
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("51561asdasbnfkasbdaksjd" + j++);
                }
            });
        }
        threadPoolExecutor.shutdown();
    }
}
