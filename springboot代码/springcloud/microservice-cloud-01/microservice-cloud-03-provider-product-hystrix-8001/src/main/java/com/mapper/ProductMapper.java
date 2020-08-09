package com.mapper;

import com.domain.Product;


import java.util.List;


public interface ProductMapper {

    Product findById(Integer pid);

   //@Select("select * from product")
    List<Product> findAll();


    Integer addProduct(Product product);


}
