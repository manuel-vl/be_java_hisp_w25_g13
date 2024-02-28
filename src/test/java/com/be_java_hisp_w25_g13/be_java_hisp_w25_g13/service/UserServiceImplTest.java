package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.ExceptionDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.NumberDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.BadRequestException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.NotFoundException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IProductRepository;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.PostRepositoryImpl;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.UserRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.Utilities.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @Mock
  UserRepositoryImpl userRepository;
  @InjectMocks
  UserServiceImpl userService;

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
}