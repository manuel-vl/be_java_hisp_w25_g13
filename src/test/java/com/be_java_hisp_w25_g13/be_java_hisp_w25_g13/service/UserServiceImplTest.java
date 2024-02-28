package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.BadRequestException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.NotFoundException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.PostRepositoryImpl;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.UserRepositoryImpl;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.Utilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepositoryImpl userRepository;
    @Mock
    PostRepositoryImpl postRepository;
    @InjectMocks
    UserServiceImpl userService;

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

}