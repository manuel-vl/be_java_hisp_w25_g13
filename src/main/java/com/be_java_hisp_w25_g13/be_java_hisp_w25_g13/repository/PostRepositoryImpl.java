package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;

import java.time.LocalDate;
import java.util.List;

public class PostRepositoryImpl implements IPostRepository{
    @Override
    public Post addPost(Post post) {
        return null;
    }

    @Override
    public List<Post> filterByDateAndIdUsuario(Integer idUsuario, LocalDate date) {
        return null;
    }
}
