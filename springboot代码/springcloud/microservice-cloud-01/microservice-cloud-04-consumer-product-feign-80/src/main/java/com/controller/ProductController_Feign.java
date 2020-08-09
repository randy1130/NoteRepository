package com.controller;


import com.domain.Product;
import com.service.ProductClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController_Feign {
    @Autowired
    private ProductClientService productClientService;

    @RequestMapping(value = "/consumer/addOne")
    Integer add(Product product) {
        return productClientService.add(product);
    }

    @RequestMapping(value = "/consumer/findOne/{pid}")
    Product get(@PathVariable("pid") Integer pid) {
        return productClientService.get(pid);
    }

    @RequestMapping(value = "/consumer/findAll")
    List<Product> list() {
        return productClientService.list();
    }
}
