package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//会扫描标记了指定包下@FeignClient注解的接口，并生成此接口的代理对象
@EnableFeignClients(basePackages = {"com"})
@EnableEurekaClient //向服务注册中心进行注册
@SpringBootApplication
public class ConsumerProviderFeign {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerProviderFeign.class, args);

    }
}