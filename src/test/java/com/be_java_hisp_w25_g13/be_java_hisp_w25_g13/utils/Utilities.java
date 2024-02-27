package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.utils;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.UserDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Post;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Product;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.Seller;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utilities {
    public static UserDTO generateUserDTO(Integer id, String name){
        return new UserDTO(id,name);
    }
    public static List<UserDTO> generateUsersDTO(){
        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(generateUserDTO(1,"Juan Manuel"));
        userDTOS.add(generateUserDTO(2,"Julian"));
        userDTOS.add(generateUserDTO(3,"Felipe"));
        return userDTOS;
    }
    public static Seller generateSeller(Integer id, String name, List<User> followers){
        return new Seller(id,name,followers);
    }
    public static User generateUser(Integer id, String name){
        return new User(id,name);
    }
    public static List<User> generateListUsers() {
        List<User> users = new ArrayList<>();
        users.add(generateUser(4,"Daniela"));
        users.add(generateUser(5,"Sebastian"));
        users.add(generateUser(6,"Manuel"));
        return users;
    }
    public static List<Seller>  generateListSellers(){
        List<Seller> sellers = new ArrayList<>();
        sellers.add(generateSeller(1, "Juan Manuel", Collections.emptyList()));
        sellers.add(generateSeller(2, "Julian", Collections.emptyList()));
        sellers.add(generateSeller(3, "Felipe", Collections.emptyList()));
        return sellers;
    }
    public static Seller generateSeller3Followed(Integer id, String name){
        return new Seller(id, name, generateListUsers());
    }
    public static User generateUser3Following(Integer id, String name){
        return new User(id, name, generateListSellers());
    }
    public static Product generateProduct(Integer id, String name){
        return new Product(id, name, "Comida", "Carulla", "Verde", "Expira en 3 dias");
    }
    public static String serializeUserDTO(UserDTO userDTO) throws JsonProcessingException{
        ObjectWriter ow = new ObjectMapper().writer();
        return ow.writeValueAsString(userDTO);
    }
    public static Post generatePost(Integer userId, Integer postId, LocalDate date, Integer productId, String productName){
        return new Post(userId, date, generateProduct(productId, productName), 0, 10.0);
    }
    public static List<Post> generateListPost(){
        List<Post> listPost = new ArrayList<>();
        listPost.add(generatePost(4,5, LocalDate.parse("2024-2-25"),6,"Laptop"));
        listPost.add(generatePost(5,6, LocalDate.parse("2024-2-25"),7,"Impresora"));
        listPost.add(generatePost(6,7, LocalDate.parse("2024-2-25"),8,"Monitor"));
        return listPost;
    }
}
