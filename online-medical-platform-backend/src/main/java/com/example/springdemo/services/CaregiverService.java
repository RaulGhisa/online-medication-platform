package com.example.springdemo.services;

import com.example.springdemo.dto.CaregiverViewDto;
import com.example.springdemo.dto.PatientViewDto;
import com.example.springdemo.dto.UserDto;
import com.example.springdemo.dto.UserViewDto;
import com.example.springdemo.dto.builders.PatientViewBuilder;
import com.example.springdemo.dto.builders.UserViewBuilder;
import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Med;
import com.example.springdemo.entities.MedicalRecord;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.users.Caregiver;
import com.example.springdemo.entities.users.Patient;
import com.example.springdemo.repositories.CaregiverRepo;
import com.example.springdemo.repositories.MedicationRepo;
import com.example.springdemo.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CaregiverService {

    private final CaregiverRepo caregiverRepo;
    private final MedicationRepo medicationRepo;

    @Autowired
    public CaregiverService(CaregiverRepo caregiverRepo, MedicationRepo medicationRepo) {
        this.caregiverRepo = caregiverRepo;
        this.medicationRepo = medicationRepo;
    }

    public CaregiverViewDto findById(Long id) {
        Caregiver caregiver = caregiverRepo.findById(id).get();
        List<Patient> patients = caregiver.getPatients();

        UserViewDto caregiverDto = UserViewBuilder.generateDtoFromEntity(caregiver);
        List<UserViewDto> patientsDto = patients.stream()
                .map((e) -> UserViewBuilder.generateDtoFromEntity(e))
                .collect(Collectors.toList());

        return new CaregiverViewDto(
                caregiverDto,
                patientsDto
        );
    }
}
