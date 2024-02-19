package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowersDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.NumberDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO followUser(Integer userId, Integer userIdToFollow);
    UserDTO addUser(UserDTO userDto);
    FollowersDTO getFollowers(Integer userId, String orderBy);
    List<UserDTO> getFollowed(Integer userId, String orderBy);
    UserDTO getUserById(Integer userId);
    NumberDTO getNumberOfFollowers(Integer userId);
}
