package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.SellerPostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.NotFoundException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IPostRepository;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IUserRepository;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.Mapper;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.Utilities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    @Mock
    IUserRepository userRepository;

    @Mock
    IPostRepository postRepository;

    @InjectMocks
    PostServiceImpl postService;

    @Test
    @DisplayName("T-08 getUserPostPerSeller OK")
    void getUserPostPerSellerTestOK(){
        Seller seller1 = new Seller(2,"Julian",Utilities.generateListUsers());
        LocalDate hourNow = LocalDate.now();
        List<Post> listPostSeller = Arrays.asList(new Post(2, hourNow.minusDays(1), Utilities.generateProduct(1, "Sushi"), 6, 15000.0),
                new Post(2, hourNow.minusWeeks(1), Utilities.generateProduct(2, "Torta"), 7, 25000.0),
                new Post(2, hourNow.minusWeeks(2), Utilities.generateProduct(1, "Arepa"), 2, 9000.0),
                new Post(2, hourNow.minusWeeks(3), Utilities.generateProduct(2, "Pan"), 3, 8000.0));
        for (Post post : listPostSeller) {
            postRepository.addPost(post);
        }
        User user1 = new User(5,"Sebastian",List.of(seller1));
        //Expected
        List<Post> listPostSellerExpected = Arrays.asList(new Post(2, hourNow.minusDays(1), Utilities.generateProduct(1, "Sushi"), 6, 15000.0),
                new Post(2, hourNow.minusWeeks(1), Utilities.generateProduct(2, "Torta"), 7, 25000.0),
                new Post(2, hourNow.minusWeeks(2), Utilities.generateProduct(1, "Arepa"), 2, 9000.0));
        SellerPostDTO sellerPostDTOExpected = new SellerPostDTO(user1.getUserId(),
                postService.orderPostList(listPostSellerExpected, "date_asc").stream().map(Mapper::mapPostToPost2DTO).toList());

        lenient().when(userRepository.getUserById(5)).thenReturn(Optional.of(user1));
        lenient().when(postRepository.filterByUserIdAndDate(2, hourNow.minusDays(14),hourNow)).thenReturn(listPostSellerExpected);

        List<Post> listPostSellerActual= postRepository.filterByUserIdAndDate(2, hourNow.minusDays(14),hourNow);
        SellerPostDTO sellerPostDTOActual = postService.getPostPerSeller(user1.getUserId(), "date_asc");


        assertThat(listPostSellerExpected).isEqualTo(listPostSellerActual);
        assertThat(sellerPostDTOExpected).isEqualTo(sellerPostDTOActual);
    }

    @Test
    @DisplayName("T-08 getUserPostPerSeller NoTFoundException")
    void getUserPostPerSellerTestNoTFoundException(){
        Seller seller1 = new Seller(2,"Julian",Utilities.generateListUsers());
        LocalDate hourNow = LocalDate.now();
        User user1 = new User(5,"Sebastian",List.of(seller1));

        lenient().when(userRepository.getUserById(5)).thenReturn(Optional.of(user1));
        lenient().when(postRepository.filterByUserIdAndDate(2, hourNow.minusDays(14),hourNow)).thenReturn(List.of());

        assertThrows(NotFoundException.class,()->postService.getPostPerSeller(user1.getUserId(), "date_asc"));
    }
    @Test
    @DisplayName("T-06 verify postListDescOrder")
    void postListOrderDateDesc() {
        LocalDate hourNow = LocalDate.now();
        User mockUser = Utilities.generateUser(1, "Juan Manuel");
        mockUser.setFollowing(List.of(new Seller(2,"Julian",Collections.emptyList())));
        List<Post> postListExpected = Arrays.asList(
                new Post(2, hourNow.minusDays(1), Utilities.generateProduct(1, "Sushi"), 6, 15000.0),
                new Post(2, hourNow.minusWeeks(2), Utilities.generateProduct(2, "Torta"), 7, 25000.0),
                new Post(2, hourNow.minusWeeks(3), Utilities.generateProduct(1, "Arepa"), 2, 9000.0),
                new Post(2, hourNow.minusWeeks(4), Utilities.generateProduct(2, "Pan"), 3, 8000.0));
        SellerPostDTO sellerPostDTOExpected = new SellerPostDTO(1,postListExpected.stream().map(Mapper::mapPostToPost2DTO).toList());
        List<Post> mockPostList = Arrays.asList(
                new Post(2, hourNow.minusWeeks(4), Utilities.generateProduct(2, "Pan"), 3, 8000.0),
                new Post(2, hourNow.minusWeeks(3), Utilities.generateProduct(1, "Arepa"), 2, 9000.0),
                new Post(2, hourNow.minusWeeks(2), Utilities.generateProduct(2, "Torta"), 7, 25000.0),
                new Post(2, hourNow.minusDays(1), Utilities.generateProduct(1, "Sushi"), 6, 15000.0));
        lenient().when(userRepository.getUserById(1)).thenReturn(Optional.of(Utilities.generateUser3Following(1,"Juan Manuel")));
        lenient().when(postRepository.filterByUserIdAndDate(2,hourNow.minusDays(14),hourNow)).thenReturn(mockPostList);

        SellerPostDTO sellerPostDTOObtained = postService.getPostPerSeller(1,"date_desc");

        assertThat(sellerPostDTOObtained).usingRecursiveComparison().isEqualTo(sellerPostDTOExpected);
    }
    @Test
    @DisplayName("T-06 verify postListAscOrder")
    void postListOrderDateAsc(){
        LocalDate hourNow = LocalDate.now();
        User mockUser = Utilities.generateUser(1, "Juan Manuel");
        mockUser.setFollowing(List.of(new Seller(2,"Julian",Collections.emptyList())));
        List<Post> postListExpected = Arrays.asList(
                new Post(2, hourNow.minusWeeks(4), Utilities.generateProduct(2, "Pan"), 3, 8000.0),
                new Post(2, hourNow.minusWeeks(3), Utilities.generateProduct(1, "Arepa"), 2, 9000.0),
                new Post(2, hourNow.minusWeeks(2), Utilities.generateProduct(2, "Torta"), 7, 25000.0),
                new Post(2, hourNow.minusDays(1), Utilities.generateProduct(1, "Sushi"), 6, 15000.0));
        SellerPostDTO sellerPostDTOExpected = new SellerPostDTO(1,postListExpected.stream().map(Mapper::mapPostToPost2DTO).toList());
        List<Post> mockPostList = Arrays.asList(
                new Post(2, hourNow.minusWeeks(4), Utilities.generateProduct(2, "Pan"), 3, 8000.0),
                new Post(2, hourNow.minusWeeks(3), Utilities.generateProduct(1, "Arepa"), 2, 9000.0),
                new Post(2, hourNow.minusWeeks(2), Utilities.generateProduct(2, "Torta"), 7, 25000.0),
                new Post(2, hourNow.minusDays(1), Utilities.generateProduct(1, "Sushi"), 6, 15000.0));
        lenient().when(userRepository.getUserById(1)).thenReturn(Optional.of(Utilities.generateUser3Following(1,"Juan Manuel")));
        lenient().when(postRepository.filterByUserIdAndDate(2,hourNow.minusDays(14),hourNow)).thenReturn(mockPostList);

        SellerPostDTO sellerPostDTOObtained = postService.getPostPerSeller(1,"date_asc");

        assertThat(sellerPostDTOObtained).usingRecursiveComparison().isEqualTo(sellerPostDTOExpected);
    }
}