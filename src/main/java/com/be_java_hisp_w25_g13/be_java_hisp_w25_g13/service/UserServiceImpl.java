package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowersDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.NumberDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.BadRequestException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.NotFoundException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<UserDTO> getFollowed(Integer userId, String orderBy) {
        return null;
    }

    @Override
    public FollowersDTO getFollowers(Integer userId, String orderBy) {
        //TODO: integrar solucion de daniela.
        Optional<User> user = userRepository.getUserById(userId);
        if(user.isEmpty()){
            throw new NotFoundException("El id de este usuario no se encuentra registrado");
        }
        if(!(user.get() instanceof Seller)){
            throw new BadRequestException("El id de este usuario no es el de un vendedor");
        }
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User userAux:
                ((Seller) user.get()).getFollowers()) {
            userDTOS.add(new UserDTO(userAux.getUserId(),userAux.getUserName()));
        }
        return new FollowersDTO(user.get().getUserId(),user.get().getUserName(),userDTOS);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        return null;
    }

    @Override
    public NumberDTO getNumberOfFollowers(Integer userId) {
        Optional<User> user = userRepository.getUserById(userId);
        if(user.isEmpty()){
            throw new NotFoundException("El id de este usuario no se encuentra registrado");
        }
        if(!(user.get() instanceof Seller)){
            throw new BadRequestException("El id de este usuario no es el de un vendedor");
        }
        return new NumberDTO(user.get().getUserId(),user.get().getUserName(),((Seller) user.get()).getFollowers().size());
    }
}
