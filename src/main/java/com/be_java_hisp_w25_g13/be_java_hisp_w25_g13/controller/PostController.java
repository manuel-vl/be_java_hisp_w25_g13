package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.controller;


import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service.IPostService;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.PostDTO;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service.IUserService;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service.PostServiceImpl;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class PostController {
    @Autowired
    ProductServiceImpl productService;
    IPostService postService;
    @Autowired
    IUserService userService;
    @GetMapping("followed/{userId}/list")
    public ResponseEntity<?> publiProdSeller(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getPostPerSeller(userId), HttpStatus.OK);}

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.addPost(postDTO), HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<?> listProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }
}
