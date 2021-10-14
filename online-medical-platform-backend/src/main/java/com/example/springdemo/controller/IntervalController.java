package com.example.springdemo.controller;


import com.example.springdemo.dto.IntervalDto;
import com.example.springdemo.dto.IntervalViewDto;
import com.example.springdemo.dto.MedicationDto;
import com.example.springdemo.dto.UserDto;
import com.example.springdemo.services.IntervalService;
import com.example.springdemo.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/interval")
public class IntervalController {

    private final IntervalService intervalService;

    @Autowired
    public IntervalController(IntervalService intervalService) {
        this.intervalService = intervalService;
    }

    @GetMapping
    public List<IntervalViewDto> findAll() {
        return intervalService.findAll();
    }

    @PostMapping
    public Long insertInterval(@RequestBody IntervalDto intervalDto) {
        return intervalService.insert(intervalDto);
    }

    @DeleteMapping("/{id}")
    public Long deleteInterval(@PathVariable Long id) {
        return intervalService.delete(id);
    }

    @PutMapping("/{id}")
    public Long updateInterval(@PathVariable Long id, @RequestBody IntervalDto intervalDto) {
        return intervalService.update(intervalDto);
    }
}
