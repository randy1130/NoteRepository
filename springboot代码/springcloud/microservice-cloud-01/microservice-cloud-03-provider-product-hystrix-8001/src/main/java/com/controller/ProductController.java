package com.controller;


import com.domain.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    //当get方法出现异常时，则会调用hystrixGet方法处理
    @HystrixCommand(fallbackMethod = "getFallback")
    @RequestMapping("/findOne/{pid}")
    public Object findById(@PathVariable("pid") Integer pid) {
        Product product = productService.findById(pid);
        //模拟异常
        if (product == null) {
            throw new RuntimeException("ID=" + pid + "无效");
        }
        return product;
    }

    //当get方法出现异常时，则会调用此方法. 注意此方法的返回值，参数列表与原方法一致
    public Object getFallback(@PathVariable("pid") Integer pid) {

        return new Product(pid + "Error");

    }


    @RequestMapping("/addOne")
    public Object addProduct(@RequestBody Product product) {

        return productService.addProduct(product);
    }

}
