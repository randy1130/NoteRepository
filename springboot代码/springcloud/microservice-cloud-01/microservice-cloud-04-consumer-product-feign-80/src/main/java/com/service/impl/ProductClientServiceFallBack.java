package com.service.impl;

import com.domain.Product;
import com.service.ProductClientService;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductClientServiceFallBack implements ProductClientService {

    @Override
    public Integer add(Product product) {
        return null;
    }

    @Override
    public Product get(Integer pid) {
        return new Product(pid + "Error");
    }

    @Override
    public List<Product> list() {
        return null;
    }
}
