package com.service.impl;

import com.domain.Product;
import com.mapper.ProductMapper;
import com.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional//管理添加事务控制
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public Product findById(Integer pid) {
        return productMapper.findById(pid);
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public Integer addProduct(Product product) {
        return productMapper.addProduct(product);
    }
}
