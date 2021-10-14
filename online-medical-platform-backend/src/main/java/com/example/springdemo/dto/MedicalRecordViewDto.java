package com.example.springdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MedicalRecordViewDto {
    private Long id;
    private String doctor;
    private String disease;
    private String hospital;
}
