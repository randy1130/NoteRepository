package com;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.mapper")//扫描这个包下的所有接口，把它变成实现类等同于@Mapper在类上
@SpringBootApplication
public class ProductProvider {
    public static void main(String[] args) {
        SpringApplication.run(ProductProvider.class, args);
    }
}
