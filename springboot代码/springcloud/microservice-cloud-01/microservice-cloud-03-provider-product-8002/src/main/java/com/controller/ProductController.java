package com.controller;


import com.domain.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    public Object findAll() {
        return productService.findAll();
    }

    @RequestMapping("/findOne/{pid}")
    public Object findById(@PathVariable("pid") Integer pid) {
        return productService.findById(pid);
    }


    @RequestMapping("/addOne")
    public Object addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

}
