package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowedDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowersDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.NumberDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.BadRequestException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.NotFoundException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IUserRepository;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;
    @Override
    public UserDTO addUser(UserDTO userDto) {
        return null;
    }

    @Override
    public FollowedDTO getFollowed(Integer userId, String orderBy) {
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
    public List<UserDTO> getAllUsers(){
        return userRepository.getAll().stream()
                .map(u -> new UserDTO(u.getUserId(), u.getUserName()))
                .toList();
    }

    @Override
    public FollowedDTO getFollowed(Integer userId){
        Optional<User> user = userRepository.getUserById(userId);
        if(user.isEmpty()){
            throw new NotFoundException("El id de este usuario no se encuentra registrado");
        }
        User foundUser = user.get();
        if(foundUser instanceof User){
            throw new BadRequestException("El id proporcionado corresponde a un Usuario, un usuario no tiene seguidores");
        }else{
            Seller foundSeller = (Seller) foundUser;
            return new FollowedDTO(
                    foundUser.getUserId(),
                    foundSeller.getUserName(),
                    foundSeller.getFollowers().stream().map(Mapper::mapUserToUserDto).toList());
        }

    }
}
