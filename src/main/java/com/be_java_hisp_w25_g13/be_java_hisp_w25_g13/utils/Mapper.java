package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowersDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    static ObjectMapper mapper = new ObjectMapper();

    public static UserDTO mapUserToUserDto(User user){
        return new UserDTO(user.getUserId(), user.getUserName());
    }
    public static FollowersDTO toFollowersDTO(User user, List<User> users){
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User userAux:
                users) {
            userDTOS.add(Mapper.mapUserToUserDto(userAux));
        }
        return new FollowersDTO(user.getUserId(),user.getUserName(),userDTOS);
    }
}
