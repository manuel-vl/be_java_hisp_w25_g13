package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class sPostRepositoryImpl implements IPostRepository{

    List<Post> posts = new ArrayList<>();
    @Override
    public Post addPost(Post post) {
        posts.add(post);

        return post;
    }

    @Override
    public List<Post> filterByDateAndIdUsuario(Integer idUsuario, LocalDate date) {
        return posts.stream().filter(x -> {
            if (x.getUser_id().equals(idUsuario) && date.compareTo(x.getDate()) <= 14) {
                return true;
            }
            return false;
        }).toList();
    }

}
