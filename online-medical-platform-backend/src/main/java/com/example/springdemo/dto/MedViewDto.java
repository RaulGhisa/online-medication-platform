package com.example.springdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MedViewDto {
    private Long id;
    private String name;
    private String dosage;
    private String sideEffect;
}
