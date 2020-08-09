package com.zr.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/***
 * Condition的定点唤醒
 * 需求：A打出1到5，B打出1到10，C打出1到15
 * ABC线程依次打出
 */
class MyData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        try {
            lock.lock();
            while (number != 0) {
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            number = 1;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        try {
            lock.lock();
            while (number != 1) {
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            number = 2;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        try {
            lock.lock();
            while (number != 2) {
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            number = 0;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class Demo {
    public static void main(String[] args) throws Exception {
        MyData myData = new MyData();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                myData.print5();
            }

        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                myData.print10();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                myData.print15();
            }
        }, "C").start();
    }
}