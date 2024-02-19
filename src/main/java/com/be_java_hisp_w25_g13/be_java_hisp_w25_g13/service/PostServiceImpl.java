package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.PostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService{
    @Autowired
    IPostRepository postRepository;
    @Override
    public PostDTO addPost(PostDTO postDTO) {
        Post post=this.DTOToPost(postDTO);
        postRepository.addPost(post);
        
        return postDTO;
    }

    @Override
    public List<PostDTO> getPosts(Integer idUsuario) {
        return null;
    }

    public PostDTO PostToDTO(Post post){
        PostDTO postDTO=new PostDTO();

        postDTO.setUser_id(post.getUser_id());
        postDTO.setDate(post.getDate());
        postDTO.setProduct(post.getProduct());
        postDTO.setCategory(post.getCategory());
        postDTO.setPrice(post.getPrice());

        return postDTO;
    }

    public Post DTOToPost(PostDTO postDTO){
        Post post=new Post();

        post.setUser_id(postDTO.getUser_id());
        post.setDate(postDTO.getDate());
        post.setProduct(postDTO.getProduct());
        post.setCategory(postDTO.getCategory());
        post.setPrice(postDTO.getPrice());

        return post;
    }
}
