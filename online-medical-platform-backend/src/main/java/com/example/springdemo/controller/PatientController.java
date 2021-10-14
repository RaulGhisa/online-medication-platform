package com.example.springdemo.controller;

import com.example.springdemo.dto.PatientCaregiverDto;
import com.example.springdemo.dto.PatientViewDto;
import com.example.springdemo.services.PatientService;
import com.example.springdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService userService) {
        this.patientService = userService;
    }

    @GetMapping("/{id}")
    public PatientViewDto findPatient(@PathVariable Long id) {
        System.out.println(id);
        return patientService.findById(id);
    }

    @PutMapping(value = "/{id}")
    public Long updatePatientCaregiver(@PathVariable Long id, @RequestBody PatientCaregiverDto patientCaregiverDto) {
        return patientService.updatePatientCaregiver(patientCaregiverDto);
    }
}
