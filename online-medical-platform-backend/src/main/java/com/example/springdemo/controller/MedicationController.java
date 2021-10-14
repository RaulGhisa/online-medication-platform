package com.example.springdemo.controller;


import com.example.springdemo.dto.MedicationDto;
import com.example.springdemo.dto.PatientViewDto;
import com.example.springdemo.services.MedicationService;
import com.example.springdemo.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(@RequestBody MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PutMapping("/{id}")
    public Long updateMedication(@PathVariable Long id, @RequestBody MedicationDto medicationDto) {
        return medicationService.update(medicationDto);
    }
}
