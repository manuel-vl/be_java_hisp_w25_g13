package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.controller;

import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.FollowersDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.NumberDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<NumberDTO> getFollowersCount(@PathVariable Integer userId){
        return ResponseEntity.ok().body(userService.getNumberOfFollowers(userId));
    }
    @GetMapping("/{userID}/followers/list")
    public ResponseEntity<FollowersDTO> getFollowersList(@PathVariable Integer userID, @RequestParam(value = "order", defaultValue = "desc") String orderBy){
        return ResponseEntity.ok().body(userService.getFollowers(userID, orderBy));
    }

}
