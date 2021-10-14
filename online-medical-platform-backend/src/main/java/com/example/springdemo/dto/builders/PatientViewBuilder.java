package com.example.springdemo.dto.builders;

import com.example.springdemo.dto.*;
import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Med;
import com.example.springdemo.entities.MedicalRecord;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.users.Caregiver;
import com.example.springdemo.entities.users.Patient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatientViewBuilder {

    public static PatientViewDto generateDtoFromEntity(Patient patient, Caregiver caregiver, Medication medication, Map<Med, IntakeInterval> medIntakeIntervals, List<MedicalRecord> medicalRecords) {

        UserViewDto caregiverDto = null;
        if (caregiver != null) caregiverDto = UserViewBuilder.generateDtoFromEntity(caregiver);

        UserViewDto patientDto = UserViewBuilder.generateDtoFromEntity(patient);

        List<MedDto> medsIntervals = MedIntervalViewBuilder.generateDtoFromEntity(medIntakeIntervals);

        MedicationViewDto medicationViewDto = new MedicationViewDto(
                medication.getId(),
                medication.getStartDate(),
                medication.getEndDate(),
                medsIntervals
        );

        List<MedicalRecordViewDto> medicalRecordsViewDto =
                medicalRecords.stream()
                .map((e) -> new MedicalRecordViewDto(e.getId(), e.getDoctor(), e.getDisease(), e.getHospital()))
                .collect(Collectors.toList());

        return new PatientViewDto(
                patientDto,
                caregiverDto,
                medicationViewDto,
                medicalRecordsViewDto
        );
    }
}
