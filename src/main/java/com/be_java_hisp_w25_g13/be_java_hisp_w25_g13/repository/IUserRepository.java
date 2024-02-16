package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;

import java.util.List;

public interface IUserRepository {
    User addUser(User user);
    List<User> getAll();
    User getUserById(Integer userId);
    Seller getSellerById(Integer userId);

}
