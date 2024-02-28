package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.PostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.SellerPostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IPostRepository;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IUserRepository;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.Utilities;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    @Mock
    IUserRepository userRepository;

    @Mock
    IPostRepository postRepository;

    @InjectMocks
    PostServiceImpl postService;

    @Test
    void getUserPostPerSellerTest(){
        Seller sellerExpected = new Seller(2,"Julian",Utilities.generateListUsers());
        LocalDate hourNow = LocalDate.now();
        LocalDate fechaUnDia = LocalDate.of(2024, 2, 27);
        LocalDate fechaUnaSemana = LocalDate.of(2024, 2, 21);
        List<Post> listPostExpected = Arrays.asList(new Post(2, fechaUnDia, Utilities.generateProduct(1, "Sushi"), 6, 15000.0),
                new Post(2, fechaUnaSemana, Utilities.generateProduct(2, "Torta"), 7, 25000.0));
        for (Post post : listPostExpected) {
            postRepository.addPost(post);
        }
        User userExpected = new User(5,"Sebastian",List.of(sellerExpected));

        Mockito.when(userRepository.getUserById(5)).thenReturn(Optional.of(userExpected));
        Mockito.when(postRepository.filterByDateAndIdUsuario(2, hourNow))
                .thenReturn(listPostExpected);

        SellerPostDTO resultDTO = postService.getPostPerSeller(userExpected.getUserId(), "date_asc");

        List<Post> orderedPosts = postService.orderPostList(listPostExpected, "date_asc");

        assertThat(orderedPosts).isSortedAccordingTo(Comparator.comparing(Post::getDate));

    }


}