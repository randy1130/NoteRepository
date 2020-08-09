package com.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration//添加配置类
public class ConfigBean {
    /**
     * @LoadBalanced 表示这个RestTemplate开启负载均衡，在调用服务提供者的接口时，
     * 可使用 服务名称 替代真实IP地址。服务名称 就是服务提供者在application.yml中
     * 配置的spring.application.name属性的值。
     */
    @LoadBalanced
    @Bean//向容器中添加 RestTemplate 组件,即将返回的对象交给spring容器管理
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
