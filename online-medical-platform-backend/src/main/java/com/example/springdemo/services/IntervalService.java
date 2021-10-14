package com.example.springdemo.services;

import com.example.springdemo.dto.IntervalDto;
import com.example.springdemo.dto.IntervalViewDto;
import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Med;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.repositories.IntervalRepo;
import com.example.springdemo.repositories.MedRepo;
import com.example.springdemo.repositories.MedicationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntervalService {

    private final IntervalRepo intervalRepo;
    private final MedicationRepo medicationRepo;
    private final MedRepo medRepo;

    public IntervalService(IntervalRepo intervalRepo, MedicationRepo medicationRepo, MedRepo medRepo) {
        this.intervalRepo = intervalRepo;
        this.medicationRepo = medicationRepo;
        this.medRepo = medRepo;
    }

    public List<IntervalViewDto> findAll() {
        List<IntakeInterval> intervals = (List<IntakeInterval>) intervalRepo.findAll();

        List<IntervalViewDto> intervalsViewDto = intervals.stream()
                .map((e) -> new IntervalViewDto(e.getId(), e.getStartDate(), e.getEndDate()))
                .collect(Collectors.toList());

        return intervalsViewDto;
    }

    public Long insert(IntervalDto intervalDto) {

        System.out.println(intervalDto);

        IntakeInterval intakeInterval = new IntakeInterval();
        intakeInterval.setStartDate(intervalDto.getStartDate());
        intakeInterval.setEndDate(intervalDto.getEndDate());

        Medication medication = medicationRepo.findById(intervalDto.getMedicationId()).get();
        Med med = medRepo.findById(intervalDto.getMedId()).get();

        intakeInterval.setMedication(medication);
        intakeInterval.setMed(med);

        return intervalRepo.save(intakeInterval).getId();
    }

    public Long delete(Long id) {
        IntakeInterval intakeInterval = intervalRepo.findById(id).get();
        intervalRepo.delete(intakeInterval);

        return id;
    }

    public Long update(IntervalDto intervalDto) {
        IntakeInterval intakeInterval = intervalRepo.findById(intervalDto.getId()).get();

        intakeInterval.setStartDate(intervalDto.getStartDate());
        intakeInterval.setEndDate(intervalDto.getEndDate());

        return intervalRepo.save(intakeInterval).getId();
    }
}
