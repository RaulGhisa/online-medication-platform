package com.example.springdemo.dto;

import com.example.springdemo.entities.MedicalRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PatientViewDto {
    private UserViewDto patient;
    private UserViewDto caregiver;
    private MedicationViewDto medication;
    private List<MedicalRecordViewDto> medicalRecords;
}
