package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.PostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
    static ObjectMapper mapper = new ObjectMapper();

    public static UserDTO mapUserToUserDto(User user){
        return new UserDTO(user.getUserId(), user.getUserName());
    }

    public static Post mapPostDTOToPost(PostDTO postDTO){
        return new Post(postDTO.getUser_id(), postDTO.getDate(), postDTO.getProduct(), postDTO.getCategory(), postDTO.getPrice());
    }
}
