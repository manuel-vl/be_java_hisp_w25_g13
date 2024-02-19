package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.PostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostServiceImpl implements IPostService{
    @Autowired
    IPostRepository postRepository;
    @Override
    public PostDTO addPost(PostDTO postDTO) {
        return null;
    }

    @Override
    public List<PostDTO> getPosts(Integer idUsuario) {
        return null;
    }
}
