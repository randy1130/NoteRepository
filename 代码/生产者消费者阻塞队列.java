package com.zr.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//资源类
class MyResource {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        //输出传进来的类的类名
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while (FLAG) {
            //利用原子包装类生产新数据
            data = atomicInteger.incrementAndGet() + "";
            /**
             * 将生产的数据放入消息队列，设置时间（如果放入失败就在2秒内继续放入，如果超过2秒还是失败就返回false）
             */
            retValue = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (retValue) {
                //放入成功，
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "数据成功！");
            } else {
                //放入失败
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "数据失败！");
            }
            //等待1秒，模拟放入的时间间隔
            TimeUnit.SECONDS.sleep(1);
        }
        //FLAG为false走下面结束生产
        System.out.println("boss 叫停！");
    }

    public void myConsumer() throws Exception {
        String result = null;
        while (FLAG) {
            /**
             * 从消息对列中取出产品，（如果取出失败就在2秒内继续取，如果超过2秒还是失败就返回false）
             */
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                //经过2秒还取出失败，就结束取出，并将标志FLAG改为false，使得生产者也停
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒没有取到,消费失败！");
                return;
            }
            //取出成功
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }
    }

    public void stop() {
        //主线程关闭工厂的方法
        FLAG = false;
    }
}

public class Demo {
    public static void main(String[] args) throws Exception {
        /**
         * 自定义一个消息队列给一个容量
         */
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(3));
        //启动生产者线程
        new Thread(() -> {
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        //启动消费者线程
        new Thread(() -> {
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
        /**
         * 等待5秒后，主线程中调用方法停止生产和消费
         */
        TimeUnit.SECONDS.sleep(5);
        myResource.stop();
    }
}