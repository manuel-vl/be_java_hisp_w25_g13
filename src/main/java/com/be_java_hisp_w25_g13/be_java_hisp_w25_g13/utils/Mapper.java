package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
    static ObjectMapper mapper = new ObjectMapper();

    public static UserDTO mapUserToUserDto(User user){
        return new UserDTO(user.getUserId(), user.getUserName());
    }
}
