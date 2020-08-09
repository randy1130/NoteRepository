package com.controller;


import com.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController_Consumer {
    private static final String REST_URL_PREFIX = "http://microservice-product";
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/addOne")
    public Integer add(Product product) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/product/addOne",
                product, Integer.class);
    }

    @RequestMapping(value = "/consumer/findOne/{pid}")//@PathVariable("pid")这个注解会把uri中的pid拿到赋给参数
    public Product get(@PathVariable("pid") Integer pid) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/product/findOne/" + pid,
                Product.class);
    }

    @RequestMapping(value = "/consumer/findAll")
    public List<Product> list() {

        return restTemplate.getForObject(REST_URL_PREFIX + "/product/findAll",
                List.class);
    }

}
