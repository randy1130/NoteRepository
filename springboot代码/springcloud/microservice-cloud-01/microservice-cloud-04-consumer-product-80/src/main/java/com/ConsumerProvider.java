package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient //向服务注册中心进行注册
@SpringBootApplication
public class ConsumerProvider {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerProvider.class, args);

    }
}