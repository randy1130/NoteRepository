package com.zr.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//资源类
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        //在写入开始的时候调用读写锁的写锁开启锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入" + key);
            //线程暂停一会
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //在写入结束的时候调用读写锁的写锁关闭锁
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        //在读取开始的时候调用读写锁的读锁开启锁
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取" + key);
            //线程暂停一会
            TimeUnit.MILLISECONDS.sleep(300);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //在读取结束的时候调用读写锁的读锁关闭锁
            rwLock.readLock().unlock();
        }
    }
}

/**
 * 最后输出的结果只要是写入的两条打印结果连续，读取的不连续（其实不用管读取的连不连续都可以）
 * 这个锁保证的写入的原子性，还对读取没有加限制，既提高了高并发还保证的数据的安全
 */
public class Demo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        /**
         * 开多个线程去写入数据
         */
        for (int i = 0; i < 5; i++) {
            final int j = i;
            new Thread(() -> {
                myCache.put(String.valueOf(j), String.valueOf(j));
            }, String.valueOf(i)).start();
        }
        /**
         * 开多个线程去读数据
         */
        for (int i = 0; i < 5; i++) {
            final int j = i;
            new Thread(() -> {
                myCache.get(String.valueOf(j));
            }, String.valueOf(i)).start();
        }
    }
}

