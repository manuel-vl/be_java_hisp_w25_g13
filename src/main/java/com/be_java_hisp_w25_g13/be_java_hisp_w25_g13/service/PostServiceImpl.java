package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.PostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IPostRepository;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService{
    @Autowired
    IPostRepository postRepository;
    @Override
    public PostDTO addPost(PostDTO postDTO) {
        Post post= Mapper.mapPostDtoToPost(postDTO);
        postRepository.addPost(post);

        return postDTO;
    }

    @Override
    public List<PostDTO> getPosts(Integer idUsuario) {
        return null;
    }
}
