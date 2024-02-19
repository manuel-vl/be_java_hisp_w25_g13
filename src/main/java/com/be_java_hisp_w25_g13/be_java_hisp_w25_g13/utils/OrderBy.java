package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderBy {
    public List<User> orderByUserAsc(List<User> listUsers){
        listUsers.stream().sorted(Comparator.comparing(User::getUserName)).forEach(System.out::println);
        return listUsers;
    }
    public List<User> orderByUserDes(List<User> listUsers){
        listUsers.stream().sorted((x,y)->y.getUserName().compareTo(x.getUserName())).forEach(System.out::println);
        return listUsers;
    }

    public List<Post> orderByDateAsc(List<Post> listPosts){
        listPosts.stream().sorted(Comparator.comparing(Post::getDate)).forEach(System.out::println);
        return listPosts;
    }
    public List<Post> orderByDateDes(List<Post> listPosts){
        listPosts.stream().sorted((x,y)->y.getDate().compareTo(x.getDate())).forEach(System.out::println);
        return listPosts;
    }

}
