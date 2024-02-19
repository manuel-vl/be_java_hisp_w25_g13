package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowersDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.NumberDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.SellerPostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.NotFoundException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IPostRepository;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;
    @Autowired
    IPostRepository postRepository;
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

    @Override
    public SellerPostDTO getPostPerSeller(Integer id) {
        Optional<User> user = userRepository.getUserById(id);
        if (!user.isPresent()){
            throw new NotFoundException("El usuario no ha sido encontrado");
        }
        LocalDate hourNow = LocalDate.now();
        user.get().getFollowing().stream().filter( x -> {
            if (postRepository.filterByDateAndIdUsuario(x.getUserId(), hourNow).isEmpty()){
                return false;
            }
            return true;
        }).map( x -> {

        })
    }


}
