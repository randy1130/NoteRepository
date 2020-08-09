package com.test;


class MyData {
    /***
     * 测试的数据data
     */
    private volatile int data = 0;

    public void addData() {
        data = 60;
    }

    public int getData() {
        return data;
    }
}

public class Test01 {
    public static void main(String[] args) {
        /**
         * 这个位置只能放个对象，直接放值的话会自动被
         * final修饰，导致下面新的线程无法改变它，即无法
         * 测试
         */
        MyData myData = new MyData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 让睡一秒，以保证主线程先走到下面的while
                 */
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myData.addData();
            }
        }).start();
        /**
         * 当新开的线程改变了值被主线程看见了，这个循坏才会跳出
         *打出最下面的一句话
         */
        while (myData.getData() == 0) {
        }
        /**
         * 如果变量data没有加volatile关键字，主线程看不见新线程所
         * 改变后的值，会一直循环上面的while，打不过结果。
         * 如果加了data加了volatile关键字，主线程课可见，则打出一下的
         * 值为60
         */
        System.out.println("data=======" + myData.getData());
    }


}
