package com.example.springdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicalRecordDto {
    private Long id;
    private Long patientId;
    private String doctor;
    private String hospital;
    private String disease;
}
