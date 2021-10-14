package com.example.springdemo.controller;

import com.example.springdemo.dto.LoginDto;
import com.example.springdemo.dto.UserViewDto;
import com.example.springdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserViewDto login(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto);
        return userService.login(loginDto);
    }

}
