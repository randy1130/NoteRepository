package com.zr.providerticket.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.stereotype.Component;

@EnableDubbo
@Component
@Service//为dubbo下的service,将服务发布出去
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《无间道》";
    }
}
