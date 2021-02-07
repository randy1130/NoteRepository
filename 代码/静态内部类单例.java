package com.sxt;

/**
 * 内部类单例模式
 */

public class SingleTon {
    private SingleTon() {
        System.out.println("SingleTon 构造方法执行了。");
    }

    private static class SingleTonHoler {
        private static SingleTon INSTANCE = new SingleTon();
    }

    public static SingleTon getInstance() {
        return SingleTonHoler.INSTANCE;
    }

}