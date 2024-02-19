package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.Post2DTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.ProductDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Product;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
    static ObjectMapper mapper = new ObjectMapper();
    public static ProductDTO mapProductToProductDTO(Product product){
        return new ProductDTO(product.getProduct_id(),product.getProduct_name(), product.getType(),product.getBrand(),product.getColor(), product.getNotes());
    }
    public static Post2DTO mapPostToPost2DTO(Post post){
        return new Post2DTO(post.getUser_id(), post.getPost_id(), post.getDate(),mapProductToProductDTO(post.getProduct()), post.getCategory(), post.getPrice());
    }
    public static UserDTO mapUserToUserDto(User user){
        return new UserDTO(user.getUserId(), user.getUserName());
    }
}
