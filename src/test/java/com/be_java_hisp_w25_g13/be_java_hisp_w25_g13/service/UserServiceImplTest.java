package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.BadRequestException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.NotFoundException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.UserRepositoryImpl;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.Utilities;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepositoryImpl userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void followUserOk() {
        //ARRANGE
        Integer userId = 1;
        Integer userIdToFollow = 2;
        when(userRepository.getUserById(anyInt())).thenReturn(Optional.of(Utilities.generateUser(1,"juan")));
        when(userRepository.getUserById(anyInt())).thenReturn(Optional.of(Utilities.generateSeller(2,"Ana", new ArrayList<>())));
        //ACT
        userService.followUser(userId,userIdToFollow);
        //ASSERT
        verify(userRepository, atLeast(2)).getUserById(anyInt());
    }

    @Test
    void followUserSellerNotFound(){
        Integer userId = 1;
        Integer userIdToFollow = 2;
        when(userRepository.getUserById(anyInt())).thenReturn(Optional.of(Utilities.generateUser(1,"juan")));
        when(userRepository.getUserById(anyInt())).thenReturn(Optional.ofNullable(null));

        assertThrows(NotFoundException.class, () -> userService.followUser(userId,userIdToFollow));
    }

    @Test
    void followingUserUserNotFound(){
        Integer userId = 1;
        Integer userIdToFollow = 2;
        when(userRepository.getUserById(anyInt())).thenReturn(Optional.ofNullable(null));
        assertThrows(NotFoundException.class, () -> userService.followUser(userId,userIdToFollow));
    }

    @Test
    void followingUserUserEqualsSeller(){
        Integer id = 1;
        assertThrows(BadRequestException.class, () -> userService.followUser(id,id));
    }

    @Test
    void followingUserSellerIsNotSeller(){
        Integer userId = 1;
        Integer userIdToFollow = 2;

        when(userRepository.getUserById(anyInt())).thenReturn(Optional.of(Utilities.generateUser(1,"juan")));
        when(userRepository.getUserById(anyInt())).thenReturn(Optional.of(Utilities.generateUser(2,"Ana")));


        assertThrows(BadRequestException.class, () -> userService.followUser(userId,userIdToFollow));

    }

    @Test
    void followingUserSellerAlreadyFollow(){
        Integer userId = 4;
        Integer userIdToFollow = 90;
        when(userRepository.getUserById(userId)).thenReturn(Optional.of(Utilities.generateUser3Following(4,"Daniela")));
        when(userRepository.getUserById(userIdToFollow)).thenReturn(Optional.of(Utilities.generateSeller(90,"Juan Manuel", Utilities.generateListUsers())));


        assertThrows(BadRequestException.class, () -> userService.followUser(userId,userIdToFollow));
    }
}