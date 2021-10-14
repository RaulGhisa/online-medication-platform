package com.example.springdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MedDto {
    private Long id;
    private String name;
    private String dosage;
    private String sideEffect;
    private IntakeIntervalDto intakeInterval;
}
