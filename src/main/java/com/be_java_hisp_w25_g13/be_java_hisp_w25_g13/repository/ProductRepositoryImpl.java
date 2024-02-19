package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements IProductRepository{
    List<Product> products=new ArrayList<>();

    @Override
    public Product addProduct(Product product) {
        products.add(product);

        return product;
    }

    @Override
    public List<Product> getAll() {
        return this.products;
    }
}
