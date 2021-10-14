package com.example.springdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class MedIntervalDto {
    Map<MedDto, IntakeIntervalDto> medsIntervals;
}
