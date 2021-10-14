package com.example.springdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientCaregiverDto {
    private Long patientId;
    private Long caregiverId;
}
