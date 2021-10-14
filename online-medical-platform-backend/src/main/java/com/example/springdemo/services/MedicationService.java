package com.example.springdemo.services;

import com.example.springdemo.dto.MedicationDto;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.repositories.MedicationRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicationService {

    private final MedicationRepo medicationRepo;

    public MedicationService(MedicationRepo medicationRepo) {
        this.medicationRepo = medicationRepo;
    }

    public Long update(MedicationDto medicationDto) {
        Medication medication = medicationRepo.findById(medicationDto.getId()).get();

        medication.setStartDate(medicationDto.getStartDate());
        medication.setEndDate(medicationDto.getEndDate());

        return medicationRepo.save(medication).getId();
    }
}
