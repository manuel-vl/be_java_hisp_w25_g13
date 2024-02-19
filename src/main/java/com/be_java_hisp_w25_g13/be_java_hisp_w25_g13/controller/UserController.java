package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  @PostMapping("/users/{userId}/follow/{userIdToFollow}" )
  public void follow() {

  }
}
