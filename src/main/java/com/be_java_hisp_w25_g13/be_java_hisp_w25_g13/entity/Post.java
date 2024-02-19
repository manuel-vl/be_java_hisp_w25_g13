package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    static Integer numPost = 0;
    Integer userId;
    Integer postId;
    LocalDate date;
    Product product;
    Integer category;
    Double price;

    public Post(Integer user_id, LocalDate date, Product product, Integer category, Double price){
        numPost += 1;
        this.postId = numPost;
        this.userId = user_id;
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
    }
}
