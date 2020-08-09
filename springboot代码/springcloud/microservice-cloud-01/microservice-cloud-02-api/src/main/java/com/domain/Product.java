package com.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private Integer pid;
    private String productName;
    private String dbsource;

    public Product(String productName) {
        this.productName = productName;
    }
}
