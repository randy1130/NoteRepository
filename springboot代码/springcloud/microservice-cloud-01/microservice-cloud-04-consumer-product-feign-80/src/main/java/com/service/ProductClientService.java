package com.service;

import com.domain.Product;
import com.service.impl.ProductClientServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "microservice-product",
        fallback = ProductClientServiceFallBack.class)
public interface ProductClientService {
    @RequestMapping(value = "/product/addOne")
    Integer add(Product product);


    @RequestMapping(value = "/product/findOne/{pid}")
    Product get(@PathVariable("pid") Integer pid);


    @RequestMapping(value = "/product/findAll")
    List<Product> list();

}
