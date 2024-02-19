package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowedDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowersDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.NumberDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.SellerPostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;

import java.util.List;

public interface IUserService {
    void followUser(Integer userId, Integer userIdToFollow);
    UserDTO addUser(UserDTO userDto);
    FollowersDTO getFollowers(Integer userId, String orderBy);
    FollowedDTO getFollowed(Integer userId, String orderBy);
    FollowedDTO getFollowed(Integer userId);
    UserDTO getUserById(Integer userId);
    NumberDTO getNumberOfFollowers(Integer userId);
    SellerPostDTO getPostPerSeller(Integer id);
    List<UserDTO> getAllUsers();
}
