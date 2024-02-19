package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Product;

import java.util.List;

public interface IProductRepository {
    Product addProduct(Product product);
    List<Product> getAll();
}
