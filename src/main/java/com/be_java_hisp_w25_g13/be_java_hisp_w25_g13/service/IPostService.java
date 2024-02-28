package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.PostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.SellerPostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;

import java.util.List;

public interface IPostService {
    PostDTO addPost(PostDTO postDTO);
    SellerPostDTO getPostPerSeller(Integer id, String order);
    List<PostDTO> getPosts(Integer idUsuario);


}
