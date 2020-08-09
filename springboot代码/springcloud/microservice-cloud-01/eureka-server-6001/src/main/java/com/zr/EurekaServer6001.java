package com.zr;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer//标识一个EurekaServer服务注册中心
@SpringBootApplication
public class EurekaServer6001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer6001.class, args);
    }
}
