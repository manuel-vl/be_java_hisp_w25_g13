package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowersDTO;
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

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepositoryImpl userRepository;

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

}