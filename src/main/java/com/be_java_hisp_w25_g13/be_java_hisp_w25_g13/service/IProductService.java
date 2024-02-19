package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO addProduct(ProductDTO productDTO);
    List<ProductDTO> getProducts();
}
