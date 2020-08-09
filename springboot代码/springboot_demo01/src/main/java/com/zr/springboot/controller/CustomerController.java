package com.zr.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from department");
        System.out.println(list.get(0));
        return list.get(0).toString();
    }
}
