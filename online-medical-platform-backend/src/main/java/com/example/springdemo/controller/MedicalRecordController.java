package com.example.springdemo.controller;


import com.example.springdemo.dto.IntervalDto;
import com.example.springdemo.dto.IntervalViewDto;
import com.example.springdemo.dto.MedicalRecordDto;
import com.example.springdemo.services.IntervalService;
import com.example.springdemo.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/record")
public class MedicalRecordController {

    private final RecordService recordService;

    @Autowired
    public MedicalRecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public Long insertRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        System.out.println(medicalRecordDto);
        return recordService.insert(medicalRecordDto);
    }

    @DeleteMapping("/{id}")
    public Long deleteRecord(@PathVariable Long id) {
        return recordService.delete(id);
    }

    @PutMapping("/{id}")
    public Long updateRecord(@PathVariable Long id, @RequestBody MedicalRecordDto medicalRecordDto) {
        return recordService.update(medicalRecordDto);
    }
}
