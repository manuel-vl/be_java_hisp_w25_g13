package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowersDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.NumberDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.NotFoundException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDTO followUser(Integer userId, Integer userIdToFollow) {
        Optional<User> optionalUser = userRepository.getUserById(userId);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("El id de este usuario no se encuentra registrado");
        }

        Optional<Seller> optionalUserToFollow = userRepository.getSellerById(userIdToFollow);
        if (optionalUserToFollow.isEmpty()) {
            throw new NotFoundException("El id del vendedor a seguir no se encuentra registrado");
        }

        User user = optionalUser.get();
        Seller userToFollow = optionalUserToFollow.get();

        user.getFollowing().add(userToFollow);
        userToFollow.getFollowers().add(user);
    }

    @Override
    public UserDTO addUser(UserDTO userDto) {
        return null;
    }

    @Override
    public List<UserDTO> getFollowed(Integer userId, String orderBy) {
        return null;
    }

    @Override
    public FollowersDTO getFollowers(Integer userId, String orderBy) {
        //TODO: verificar id
        //TODO: verificar usuario seller
        //TODO: obtener followers
        //TODO: mapear DTO y retornarlo.

        return null;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        return null;
    }

    @Override
    public NumberDTO getNumberOfFollowers(Integer userId) {
        //TODO: verificar existencia usuario
        //TODO: verificar id usuario seller
        //TODO: obtener nombre del usuario
        //TODO: obtener numero de seguidores
        //TODO: retornar DTO
        Optional<User> user = userRepository.getUserById(userId);
        if(user.isEmpty()){
            throw new NotFoundException("El id de este usuario no se encuentra registrado");
        }
        if(!(user.get() instanceof Seller)){

        }

        return null;
    }
}
