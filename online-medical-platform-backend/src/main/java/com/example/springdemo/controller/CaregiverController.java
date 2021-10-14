package com.example.springdemo.controller;

import com.example.springdemo.dto.CaregiverViewDto;
import com.example.springdemo.services.CaregiverService;
import com.example.springdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver")
public class CaregiverController {

    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    @GetMapping("/{id}")
    public CaregiverViewDto findCaregiver(@PathVariable Long id) {
        System.out.println(id);
        return caregiverService.findById(id);
    }}
