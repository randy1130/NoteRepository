package com.sxt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2021-2-5 21:45:00");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String time = format.format(new Date());
                System.out.println(time + "---->定时任务执行了！");
            }
        }, date, 10 * 1000L);// 1.任务 2.开始时间 3.间隔的毫秒值

    }
}

