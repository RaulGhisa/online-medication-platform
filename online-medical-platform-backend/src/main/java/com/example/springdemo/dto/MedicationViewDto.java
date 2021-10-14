package com.example.springdemo.dto;

import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Med;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class MedicationViewDto {
    private Long id;
    private String startDate;
    private String endDate;
    private List<MedDto> meds;
}
