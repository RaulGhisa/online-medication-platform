package com.example.springdemo.services;

import com.example.springdemo.dto.PatientCaregiverDto;
import com.example.springdemo.dto.PatientViewDto;
import com.example.springdemo.dto.UserViewDto;
import com.example.springdemo.dto.builders.PatientViewBuilder;
import com.example.springdemo.dto.builders.PersonViewBuilder;
import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Med;
import com.example.springdemo.entities.MedicalRecord;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.users.Caregiver;
import com.example.springdemo.entities.users.Patient;
import com.example.springdemo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService {

    private final PatientRepo patientRepo;
    private final CaregiverRepo caregiverRepo;
    private final MedicationRepo medicationRepo;
    private final MedRepo medRepo;
    private final IntakeIntervalRepo intakeIntervalRepo;

    @Autowired
    public PatientService(PatientRepo patientRepo, CaregiverRepo caregiverRepo, MedicationRepo medicationRepo, MedRepo medRepo, IntakeIntervalRepo intakeIntervalRepo) {
        this.patientRepo = patientRepo;
        this.caregiverRepo = caregiverRepo;
        this.medicationRepo = medicationRepo;
        this.medRepo = medRepo;
        this.intakeIntervalRepo = intakeIntervalRepo;
    }

    public PatientViewDto findById(Long id) {
        Patient patient = patientRepo.findById(id).get();
        Caregiver caregiver = patient.getCaregiver();

        Medication medication = medicationRepo.findByPatient(patient).get();

        List<IntakeInterval> intakeIntervals = intakeIntervalRepo.findByMedication(medication);

        Map<Med, IntakeInterval> medIntakeIntervals = new HashMap<>();
        intakeIntervals.forEach((e) -> medIntakeIntervals.put(medRepo.findByIntakeIntervals(e).get(), e));

        List<MedicalRecord> medicalRecords = patient.getMedicalRecords();

        return PatientViewBuilder.generateDtoFromEntity(patient, caregiver, medication, medIntakeIntervals, medicalRecords);
    }

    public Long updatePatientCaregiver(PatientCaregiverDto patientCaregiverDto) {
        Patient patient = patientRepo.findById(patientCaregiverDto.getPatientId()).get();
        Caregiver caregiver = caregiverRepo.findById(patientCaregiverDto.getCaregiverId()).get();

        patient.setCaregiver(caregiver);

        return patientRepo.save(patient).getId();
    }
}
