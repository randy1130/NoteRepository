package com;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix //开启 Hystrix 熔断机制的支持
@EnableEurekaClient //本服务启动后会自动注册进Eureka服务中
@MapperScan("com.mapper")//扫描这个包下的所有接口，把它变成实现类等同于@Mapper在类上
@SpringBootApplication
public class ProductProvider {
    public static void main(String[] args) {
        SpringApplication.run(ProductProvider.class, args);
    }
}
