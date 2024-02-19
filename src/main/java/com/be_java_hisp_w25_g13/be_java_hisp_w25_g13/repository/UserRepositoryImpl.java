package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.repository;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Repository
public class UserRepositoryImpl implements IUserRepository{

    List<User> users;

    public UserRepositoryImpl() {

        this.users = loadUserDataBase();
        this.users.addAll(loadSellerDataBase());
        this.users.add(new Seller(99,"prueba seller", Arrays.asList(
                users.get(0),
                users.get(1),
                users.get(2)
        )));
        this.users.add(new Seller(100,"John Travolta", Arrays.asList(
                users.get(13),
                users.get(12),
                users.get(14)
        )));
        this.users.add(new Seller(101,"Leo Messi", Arrays.asList(
                users.get(8),
                users.get(9),
                users.get(10)
        )));
        this.users.add(new Seller(102,"Neymar Jr", Arrays.asList(
                users.get(8),
                users.get(9),
                users.get(10)
        )));
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
    }
    private List<User> loadUserDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeRef = new TypeReference<>() {};
        List<User> users = null;
        try {
            users = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private List<Seller> loadSellerDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:sellers.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Seller>> typeRef = new TypeReference<>() {};
        List<Seller> sellers = null;
        try {
            sellers = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sellers;
    }
}
