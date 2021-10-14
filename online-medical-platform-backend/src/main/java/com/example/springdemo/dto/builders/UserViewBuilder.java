package com.example.springdemo.dto.builders;

import com.example.springdemo.dto.UserViewDto;
import com.example.springdemo.entities.users.User;

public class UserViewBuilder {

    public static UserViewDto generateDtoFromEntity(User user){
        return new UserViewDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getGender(),
                user.getAddress(),
                user.getType()
        );
    }
}
