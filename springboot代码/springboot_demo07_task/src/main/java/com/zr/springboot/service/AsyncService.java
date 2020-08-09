package com.zr.springboot.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    @Scheduled(cron = "0/5 * * * * 0-7")
    public void hello() {
        System.out.println("Hello ....");
    }
}
