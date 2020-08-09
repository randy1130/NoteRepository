package com.service;

import com.domain.Product;

import java.util.List;

public interface ProductService {

    Product findById(Integer pid);

    List<Product> findAll();

    Integer addProduct(Product product);

}
