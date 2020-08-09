package com.zr.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.zr.springboot.mapper")
@EnableCaching//开启基于注解的缓存
public class SpringbootDemo04CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo04CacheApplication.class, args);

    }
}
