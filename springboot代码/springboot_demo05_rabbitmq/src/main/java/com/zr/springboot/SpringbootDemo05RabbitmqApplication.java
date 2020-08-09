package com.zr.springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit  //开启基于注解消息队列(RabbitMQ)
@SpringBootApplication
public class SpringbootDemo05RabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo05RabbitmqApplication.class, args);
    }
}
