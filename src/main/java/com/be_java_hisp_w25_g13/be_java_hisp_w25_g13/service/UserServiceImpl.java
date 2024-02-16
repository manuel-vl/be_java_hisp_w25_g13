package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.NumberDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;

import java.util.List;

public class UserServiceImpl implements IUserService{
    @Override
    public UserDTO addUser(UserDTO userDto) {
        return null;
    }

    @Override
    public List<UserDTO> getFollowed(Integer userId, String orderBy) {
        return null;
    }

    @Override
    public List<UserDTO> getFollowers(Integer userId, String orderBy) {
        return null;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        return null;
    }

    @Override
    public NumberDTO getNumberOfFollowers(Integer userId) {
        return null;
    }
}
