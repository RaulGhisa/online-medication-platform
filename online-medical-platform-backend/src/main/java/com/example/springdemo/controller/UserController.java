package com.example.springdemo.controller;

import com.example.springdemo.dto.PatientCaregiverDto;
import com.example.springdemo.dto.UserDto;
import com.example.springdemo.dto.UserViewDto;
import com.example.springdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/user/{userType}")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserViewDto> findAll(@PathVariable String userType) {
        return userService.findAll(userType);
    }

    @PostMapping
    public Long insertUser(@PathVariable String userType, @RequestBody UserDto userDto) {
        userDto.setType(userType);
        return userService.insert(userDto);
    }

    @PutMapping(value = "/{id}")
    public Long updateUser(@PathVariable String userType, @RequestBody UserDto userDto) {
        userDto.setType(userType);
        return userService.update(userDto);
    }

    @DeleteMapping(value = "/{id}")
    public Long deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
}
