package com.example.springdemo.controller;


import com.example.springdemo.dto.IntervalDto;
import com.example.springdemo.dto.MedViewDto;
import com.example.springdemo.services.MedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/med")
public class MedController {

    private final MedService medService;

    @Autowired
    public MedController(@RequestBody MedService medService) {
        this.medService = medService;
    }

    @GetMapping
    public List<MedViewDto> findAllMeds() {
        return medService.findAll();
    }
}
