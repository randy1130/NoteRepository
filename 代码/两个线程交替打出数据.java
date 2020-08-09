package com.zr.test;

/***
 * 两个线程交替输出数据递加
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Time time = new Time(1);
        Thread thread1 = new Thread(new MyRunable1(time));
        thread1.setName("t1");
        Thread thread2 = new Thread(new MyRunable2(time));
        thread2.setName("t2");
        thread1.start();
        Thread.sleep(1000);//等待一秒让加奇数的先执行
        thread2.start();
    }
}

class Time {
    private int i;

    Time(int i) {
        this.i = i;
    }

    public synchronized void addJiShu() {
        System.out.println(Thread.currentThread().getName() + "->" + (i++));
        //唤醒下一个方法线程
        this.notifyAll();
        try {
            //等待一秒，让输出的不那么快
            Thread.sleep(1000);
            //让本线程等待，保证下次执行另一个线程（达到线程交替的要求）
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addOuShu() {
        System.out.println(Thread.currentThread().getName() + "->" + (i++));
        this.notifyAll();
        try {
            Thread.sleep(1000);
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyRunable1 implements Runnable {
    private Time time;

    MyRunable1(Time time) {
        this.time = time;
    }

    @Override
    public void run() {
        while (true) {
            time.addJiShu();
        }
    }
}

class MyRunable2 implements Runnable {
    private Time time;

    MyRunable2(Time time) {
        this.time = time;
    }

    @Override
    public void run() {
        while (true) {
            time.addOuShu();
        }
    }
}