package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.*;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.BadRequestException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.NotFoundException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.PostRepositoryImpl;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.UserRepositoryImpl;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.Utilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import static com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.Utilities.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepositoryImpl userRepository;
    @Mock
    PostRepositoryImpl postRepository;
    @InjectMocks
    UserServiceImpl userService;

    @DisplayName("T-03 FollowersAsc")
    @Test
    void getFollowersOrderAscPresent() {
        //Arrange
        Seller seller = Utilities.generateSeller3Followed(1, "Pepe");
        String orderBy = "name_asc";
        when(userRepository.getUserById(1)).thenReturn(Optional.of(seller));
        //Act & Assert
        assertDoesNotThrow(() -> userService.getFollowers(1, orderBy));
        verify(userRepository, atLeastOnce()).getUserById(1);
    }
    @DisplayName("T-03 FollowersDesc")
    @Test
    void getFollowersOrderDescPresent() {
        //Arrange
        Seller seller = Utilities.generateSeller3Followed(1, "Pepe");
        String orderBy = "name_desc";
        when(userRepository.getUserById(1)).thenReturn(Optional.of(seller));
        //Act & Assert
        assertDoesNotThrow(() -> userService.getFollowers(1, orderBy));
        verify(userRepository, atLeastOnce()).getUserById(1);
    }
    @DisplayName("T-03 FollowersNoneOrder")
    @Test
    void getFollowersOrderAbsent() {
        //Arrange
        Seller seller = Utilities.generateSeller3Followed(1, "Pepe");
        String orderBy = "none";
        when(userRepository.getUserById(1)).thenReturn(Optional.of(seller));
        //Act & Assert
        assertDoesNotThrow(() -> userService.getFollowers(1, orderBy));
        verify(userRepository, atLeastOnce()).getUserById(1);
    }
    @DisplayName("T-03 FollowersInvalidOrder")
    @Test
    void getFollowersOrderInvalid() {
        //Arrange
        Seller seller = Utilities.generateSeller3Followed(1, "Pepe");
        String orderBy = "";
        when(userRepository.getUserById(1)).thenReturn(Optional.of(seller));
        //Act & Assert
        assertThrows(BadRequestException.class,() -> userService.getFollowers(1, orderBy));
    }
    @DisplayName("T-03 SellerNotFound")
    @Test
    void getFollowersSellerIdNotFound() {
        //Arrange
        Seller seller = Utilities.generateSeller3Followed(1, "Pepe");
        String orderBy = "none";
        when(userRepository.getUserById(1)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(NotFoundException.class,() -> userService.getFollowers(1, orderBy));
    }
    @DisplayName("T-03 NotSellerId")
    @Test
    void getFollowersNotSellerId() {
        //Arrange
        User user = Utilities.generateUser(1,"Pepe");
        String orderBy = "none";
        when(userRepository.getUserById(1)).thenReturn(Optional.of(user));

        //Act & Assert
        assertThrows(BadRequestException.class,() -> userService.getFollowers(1, orderBy));
    }
    @DisplayName("T-03 SellerNoFollowers")
    @Test
    void getFollowersNoFollowers() {
        //Arrange
        Seller seller = Utilities.generateSeller(1, "Pepe", Collections.emptyList());
        String orderBy = "none";
        when(userRepository.getUserById(1)).thenReturn(Optional.of(seller));

        //Act & Assert
        assertThrows(NotFoundException.class,() -> userService.getFollowers(1, orderBy));
    }
    @DisplayName("T-03 FollowedAsc")
    @Test
    void getFollowedOrderAscPresent() {
        //Arrange
        User user = Utilities.generateUser3Following(1, "Pepe");
        String orderBy = "name_asc";
        when(userRepository.getUserById(1)).thenReturn(Optional.of(user));
        //Act & Assert
        assertDoesNotThrow(() -> userService.getFollowed(1, orderBy));
        verify(userRepository, atLeastOnce()).getUserById(1);
    }
    @DisplayName("T-03 FollowedDesc")
    @Test
    void getFollowedOrderDescPresent() {
        //Arrange
        User user = Utilities.generateUser3Following(1, "Pepe");
        String orderBy = "name_desc";
        when(userRepository.getUserById(1)).thenReturn(Optional.of(user));
        //Act & Assert
        assertDoesNotThrow(() -> userService.getFollowed(1, orderBy));
        verify(userRepository, atLeastOnce()).getUserById(1);
    }
    @DisplayName("T-03 FollowedNoneOrder")
    @Test
    void getFollowedOrderAbsent() {
        //Arrange
        User user = Utilities.generateUser3Following(1, "Pepe");
        String orderBy = "none";
        when(userRepository.getUserById(1)).thenReturn(Optional.of(user));
        //Act & Assert
        assertDoesNotThrow(() -> userService.getFollowed(1, orderBy));
        verify(userRepository, atLeastOnce()).getUserById(1);
    }
    @DisplayName("T-03 InvalidOrder")
    @Test
    void getFollowedOrderInvalid() {
        //Arrange
        User user = Utilities.generateUser3Following(1, "Pepe");
        String orderBy = "";
        when(userRepository.getUserById(1)).thenReturn(Optional.of(user));
        //Act & Assert
        assertThrows(BadRequestException.class,() -> userService.getFollowed(1, orderBy));
    }
    @DisplayName("T-03 UserNotFound")
    @Test
    void getFollowedUserIdNotFound() {
        //Arrange
        User user = Utilities.generateUser3Following(1, "Pepe");
        String orderBy = "none";
        when(userRepository.getUserById(1)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(NotFoundException.class,() -> userService.getFollowed(1, orderBy));
    }
    @DisplayName("T-03 UserNotFollowed")
    @Test
    void getFollowedNoFollowed() {
        //Arrange
        User user = Utilities.generateUser(1, "Pepe");
        String orderBy = "none";
        when(userRepository.getUserById(1)).thenReturn(Optional.of(user));

        //Act & Assert
        assertThrows(NotFoundException.class,() -> userService.getFollowed(1, orderBy));
    }
    @Test
    void unFollowUserOK(){
        User userExpected = Utilities.generateUser(4,"Daniela");
        Seller sellerExpected = Utilities.generateSeller(2,"Julian",Utilities.generateListUsers());
        userExpected.getFollowing().remove(sellerExpected);
        sellerExpected.getFollowers().remove(userExpected);
        Optional<User> mockUser = Optional.of(Utilities.generateUser(4,"Daniela"));
        Optional<User> mockSeller = Optional.of(Utilities.generateSeller(2,"Julian",Utilities.generateListUsers()));
        when(userRepository.getUserById(4)).thenReturn(mockUser);
        when(userRepository.getUserById(2)).thenReturn(mockSeller);

        userService.unFollowUser(4,2);

        assertThat(mockUser.get()).usingRecursiveComparison().isEqualTo(userExpected);
        assertThat(mockSeller.get()).usingRecursiveComparison().isEqualTo(sellerExpected);
    }
    @Test
    void unFollowUserNotFoundUser(){
        Optional<User> mockUser = Optional.empty();
        Optional<User> mockSeller = Optional.of(Utilities.generateSeller(2,"Julian",Utilities.generateListUsers()));
        when(userRepository.getUserById(4)).thenReturn(mockUser);
        when(userRepository.getUserById(2)).thenReturn(mockSeller);

        Assertions.assertThrows(NotFoundException.class, () -> userService.unFollowUser(4,2));
    }
    @Test
    void unFollowUserNotFoundSeller(){
        Optional<User> mockUser = Optional.of(Utilities.generateUser(4,"Daniela"));
        Optional<User> mockSeller = Optional.empty();
        when(userRepository.getUserById(4)).thenReturn(mockUser);
        when(userRepository.getUserById(2)).thenReturn(mockSeller);

        Assertions.assertThrows(NotFoundException.class, () -> userService.unFollowUser(4,2));
    }
    @Test
    void unFollowUserNotSeller(){
        Optional<User> mockUser = Optional.of(Utilities.generateUser(4,"Daniela"));
        Optional<User> mockSellerNotSeller = Optional.of(Utilities.generateUser(2,"Julian"));
        when(userRepository.getUserById(4)).thenReturn(mockUser);
        when(userRepository.getUserById(2)).thenReturn(mockSellerNotSeller);

        Assertions.assertThrows(NotFoundException.class, () -> userService.unFollowUser(4,2));
    }
    @Test
    void unFolloweUserNotFollowSeller(){
        Optional<User> mockUser = Optional.of(Utilities.generateUser(1,"Juan Manuel"));
        Optional<User> mockSeller = Optional.of(Utilities.generateSeller(2,"Julian",Utilities.generateListUsers()));
        when(userRepository.getUserById(1)).thenReturn(mockUser);
        when(userRepository.getUserById(2)).thenReturn(mockSeller);

        Assertions.assertThrows(BadRequestException.class, () -> userService.unFollowUser(1,2));
    }
    @Test
    void getNumberOfFollowersOkTest() {
        NumberDTO expectedNumberDTO = new NumberDTO(3, "Felipe", 3);

        when(userRepository.getUserById(anyInt())).thenReturn(Optional.of(generateSeller3Followed(3, "Felipe")));
        NumberDTO actualNumberDTO = userService.getNumberOfFollowers(3);
        assertThat(actualNumberDTO).usingRecursiveComparison().isEqualTo(expectedNumberDTO);
    }
    @Test
    void getNumberOfFollowersNotFoundTest() {
        when(userRepository.getUserById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getNumberOfFollowers(3));
    }
    @Test
    void getNumberOfFollowersBadRequestTest() {
        when(userRepository.getUserById(anyInt())).thenReturn(Optional.of(generateUser(4, "Daniela")));
        assertThrows(BadRequestException.class, () -> userService.getNumberOfFollowers(4));
    }
    @Test
    void getNumberOfFollowersNotFollowersTest() {
        when(userRepository.getUserById(anyInt())).thenReturn(Optional.of(generateSeller(3, "Felipe", List.of())));
        assertThrows(NotFoundException.class, () -> userService.getNumberOfFollowers(3));
    }
    @DisplayName("T-05 NotPostProducts")
    @Test
    void getProductsSellerDontHavePosts(){
        // Arrange
        User user=Utilities.generateUser3Following(1, "Manuel");
        String orderBy="date_asc";

        when(userRepository.getUserById(user.getUserId())).thenReturn(Optional.of(user));
        when(postRepository.filterByDateAndIdUsuario(user.getUserId(), LocalDate.now())).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(NotFoundException.class, ()->userService.getPostPerSeller(user.getUserId(), orderBy));
        verify(userRepository, atLeastOnce()).getUserById(user.getUserId());
        verify(postRepository, atLeastOnce()).filterByDateAndIdUsuario(user.getUserId(), LocalDate.now());
    }
    @DisplayName("T-05 PostProductsOrderByDateAsc")
    @Test
    void getProductsSellerDateAscOK(){
        // Arrange
        User user=Utilities.generateUser3Following(1, "Manuel");
        List<Post> postsSeller=Utilities.generateListPost();

        String orderBy="date_asc";

        when(userRepository.getUserById(user.getUserId())).thenReturn(Optional.of(user));
        when(postRepository.filterByDateAndIdUsuario(user.getUserId(), LocalDate.now())).thenReturn(postsSeller);

        // Act & Assert
        assertDoesNotThrow(()->userService.getPostPerSeller(user.getUserId(), orderBy));
        verify(userRepository, atLeastOnce()).getUserById(user.getUserId());
        verify(postRepository, atLeastOnce()).filterByDateAndIdUsuario(user.getUserId(), LocalDate.now());
    }
    @DisplayName("T-05 PostProductsOrderByDateDesc")
    @Test
    void getProductsSellerDateDescOK(){
        // Arrange
        User user=Utilities.generateUser3Following(1, "Manuel");
        List<Post> postsSeller=Utilities.generateListPost();

        String orderBy="date_desc";

        when(userRepository.getUserById(user.getUserId())).thenReturn(Optional.of(user));
        when(postRepository.filterByDateAndIdUsuario(user.getUserId(), LocalDate.now())).thenReturn(postsSeller);

        // Act & Assert
        assertDoesNotThrow(()->userService.getPostPerSeller(user.getUserId(), orderBy));
        verify(userRepository, atLeastOnce()).getUserById(user.getUserId());
        verify(postRepository, atLeastOnce()).filterByDateAndIdUsuario(user.getUserId(), LocalDate.now());
    }
    @DisplayName("T-05 PostProductsOrderByDefault")
    @Test
    void getProductsSellerDateNoneOK(){
        // Arrange
        User user=Utilities.generateUser3Following(1, "Manuel");
        List<Post> postsSeller=Utilities.generateListPost();

        String orderBy="none";

        when(userRepository.getUserById(user.getUserId())).thenReturn(Optional.of(user));
        when(postRepository.filterByDateAndIdUsuario(user.getUserId(), LocalDate.now())).thenReturn(postsSeller);

        // Act & Assert
        assertDoesNotThrow(()->userService.getPostPerSeller(user.getUserId(), orderBy));
        verify(userRepository, atLeastOnce()).getUserById(user.getUserId());
        verify(postRepository, atLeastOnce()).filterByDateAndIdUsuario(user.getUserId(), LocalDate.now());
    }
    @DisplayName("T-05 PostProductsOrderByInvalidValue")
    @Test
    void getProductsSellerDateDontOK(){
        // Arrange
        User user=Utilities.generateUser3Following(1, "Manuel");
        List<Post> postsSeller=Utilities.generateListPost();

        String orderBy="";

        when(userRepository.getUserById(user.getUserId())).thenReturn(Optional.of(user));
        when(postRepository.filterByDateAndIdUsuario(user.getUserId(), LocalDate.now())).thenReturn(postsSeller);

        // Act & Assert
        assertThrows(BadRequestException.class, ()->userService.getPostPerSeller(user.getUserId(), orderBy));
        verify(userRepository, atLeastOnce()).getUserById(user.getUserId());
        verify(postRepository, atLeastOnce()).filterByDateAndIdUsuario(user.getUserId(), LocalDate.now());
    }
    @DisplayName("T-05 UserDontExist")
    @Test
    void getProductsSellerByUserIdNotFound(){
        // Arrange
        User user=Utilities.generateUser(1, "Manuel");
        String orderBy="";

        when(userRepository.getUserById(user.getUserId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, ()-> userService.getPostPerSeller(user.getUserId(), orderBy));
        verify(userRepository, atLeastOnce()).getUserById(user.getUserId());
    }
}