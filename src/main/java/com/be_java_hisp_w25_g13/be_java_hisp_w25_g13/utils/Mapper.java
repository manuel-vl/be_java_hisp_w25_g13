package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.PostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.ProductDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Product;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
    static ObjectMapper mapper = new ObjectMapper();

    public static UserDTO mapUserToUserDto(User user){
        return new UserDTO(user.getUserId(), user.getUserName());
    }

    public static Post mapPostDtoToPost(PostDTO postDTO){
        return new Post(postDTO.getUser_id(), postDTO.getDate(), postDTO.getProduct(), postDTO.getCategory(), postDTO.getPrice());
    }

    public static ProductDTO mapProductToProductDto(Product product){
        return new ProductDTO(product.getProduct_id(), product.getBrand(), product.getType(), product.getProduct_name(), product.getColor(), product.getNotes());
    }

    public static Product mapProductDtoToProduct(ProductDTO productDTO){
        return new Product(productDTO.getProduct_id(), productDTO.getProduct_name(), productDTO.getType(), productDTO.getBrand(), productDTO.getColor(), productDTO.getNotes());
    }
}
