package com.zr.springboot.service;

import com.zr.springboot.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


/**
 *  @RabbitListener 注释的方法会监控在自己配置的消息队列中有没有消息的
 *  产生，一但监听到就会执行自己的方法
 */
@Service
public class BookService {
    /**
     * 配置监听哪个消息队列里面的内容
     * queues后面可以写队列数组
     * atguigu.news是消息队列
     */
    @RabbitListener(queues = "atguigu.news")
    public void receive(Book book) {
        System.out.println("Book:" + book);
    }
    @RabbitListener(queues = "atguigu")
    public void receiveMessage(Message message) {
        //消息的头信息
        System.out.println(message.getMessageProperties());
        //消息的内容
        System.out.println(message.getBody());
    }
}
