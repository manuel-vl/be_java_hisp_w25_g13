package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowedDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowersDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.NumberDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.BadRequestException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.NotFoundException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception.WrongDataException;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository.IUserRepository;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.Mapper;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;

    @Override
    public void followUser(Integer userId, Integer userIdToFollow) {
        if (userId.equals(userIdToFollow)) {
            throw new BadRequestException("El vendedor no se puede seguir a si mismo");
        }

        Optional<User> optionalUser = userRepository.getUserById(userId);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("El id de este usuario no se encuentra registrado");
        }

        Optional<User> optionalUserToFollow = userRepository.getUserById(userIdToFollow);
        if (optionalUserToFollow.isEmpty()) {
            throw new NotFoundException("El id del vendedor a seguir no se encuentra registrado");
        }

        User userToFollow = optionalUserToFollow.get();

        if (!(userToFollow instanceof Seller seller)) {
            throw new BadRequestException("El id del vendedor a seguir no se encuentra registrado");
        }

        User user = optionalUser.get();

        user.getFollowing().add(seller);
        seller.getFollowers().add(user);
    }

    @Override
    public UserDTO addUser(UserDTO userDto) {
        return null;
    }

    @Override
    public FollowersDTO getFollowers(Integer userId) {
        List<User> followers = getFollowersAuxFunction(userId);
        return Mapper.toFollowersDTO(userRepository.getUserById(userId).get(),followers);
    }

    @Override
    public FollowersDTO getFollowers(Integer userId, String orderBy) {

        List<User> followers = getFollowersAuxFunction(userId);
        followers = orderUserList(followers,orderBy);
        return Mapper.toFollowersDTO(userRepository.getUserById(userId).get(),followers);
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

    @Override
    public List<UserDTO> getAllUsers(){
        return userRepository.getAll().stream()
                .map(u -> new UserDTO(u.getUserId(), u.getUserName()))
                .toList();
    }

    @Override
    public FollowedDTO getFollowed(Integer userId, String OrderBy){
        Optional<User> user = userRepository.getUserById(userId);
        if(user.isEmpty()){
            throw new NotFoundException("El id de este usuario no se encuentra registrado");
        }
        User foundUser = user.get();

        List<User> followingList = orderUserList(foundUser.getFollowing().stream().map(s -> (User) s).toList(), OrderBy);

        return new FollowedDTO(
                foundUser.getUserId(),
                foundUser.getUserName(),
                followingList.stream().map(Mapper::mapUserToUserDto).toList());
    }
    private List<User> getFollowersAuxFunction(Integer userId){
        Optional<User> user = userRepository.getUserById(userId);
        if(user.isEmpty()){
            throw new NotFoundException("El id de este usuario no se encuentra registrado");
        }
        if(!(user.get() instanceof Seller)){
            throw new BadRequestException("El id de este usuario no es el de un vendedor");
        }
        return ((Seller) user.get()).getFollowers();
    }
    private List<User> orderUserList(List<User> users, String orderBy){
        if(orderBy.equalsIgnoreCase("asc")){
            return OrderBy.orderByUserAsc(users);
        }
        if(orderBy.equalsIgnoreCase("desc")){
            return OrderBy.orderByUserDes(users);
        }
        return users;
    }

}
