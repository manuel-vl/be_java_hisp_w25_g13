package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.controller;


import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service.IPostService;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.PostDTO;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/post")
public class PostController {
    @Autowired
    IPostService service;
    @GetMapping("followed/{userId}/list")
    public ResponseEntity<?> publiProdSeller(@PathVariable Integer id){
        return new ResponseEntity<>(service.getPostPerSeller(id), HttpStatus.OK);}
    PostServiceImpl postService;

    @PostMapping()
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.addPost(postDTO), HttpStatus.OK);
    }
}
