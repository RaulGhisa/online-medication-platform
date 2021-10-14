package com.example.springdemo.dto.builders;

import com.example.springdemo.dto.PatientDto;
import com.example.springdemo.dto.PersonDTO;
import com.example.springdemo.dto.UserDto;
import com.example.springdemo.entities.Person;
import com.example.springdemo.entities.users.Caregiver;
import com.example.springdemo.entities.users.Patient;
import com.example.springdemo.entities.users.User;

public class UserBuilder {

    public UserBuilder() {
    }

    public static User generateEntityFromDto(UserDto userDto) {
        if (userDto.getType().equals("patient"))
        return new Patient(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getBirthDate(),
                userDto.getGender(),
                userDto.getAddress(),
                userDto.getType(),
                null
        );
        return new Caregiver(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getBirthDate(),
                userDto.getGender(),
                userDto.getAddress(),
                userDto.getType()
        );
    }
}

