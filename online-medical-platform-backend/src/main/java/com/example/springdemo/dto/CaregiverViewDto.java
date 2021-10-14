package com.example.springdemo.dto;

import lombok.*;
import net.bytebuddy.build.ToStringPlugin;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaregiverViewDto {
    private UserViewDto caregiver;
    private List<UserViewDto> patients;
}
