package com.zr.springboot;


import com.zr.springboot.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemo05RabbitmqApplicationTests {
    /**
     * 注入消息发送模板
     */
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void contextLoads() {
        /**
         * 在消息队列中放入消息‘
         * 参数一：交换器的名字
         * 参数二：队列名
         * 参数三：需要放入的对象
         */
        /*Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是一个信息！");
        map.put("data", Arrays.asList("aaa", 111, true, 'k'));
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", map);
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", new Book("水浒传", "施耐庵"));
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("三国", "曹操"));*/
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("水浒传", "施耐庵"));
    }

    /**
     * 从消息队列中取出一个消息
     */
    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }
    /**
     * 在代码中设置交换器和队列以及绑定
     */
    @Test
    public void createExchange() {
        //创建一个交换器
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        //创建一个队列
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue"));
        //声明一个绑定
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",
                Binding.DestinationType.QUEUE, "amqpadmin.exchange",
                "haha", null));

    }
}
