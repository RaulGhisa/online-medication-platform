package com.example.springdemo.dto.builders;

import com.example.springdemo.dto.IntakeIntervalDto;
import com.example.springdemo.dto.MedDto;
import com.example.springdemo.dto.MedIntervalDto;
import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Med;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MedIntervalViewBuilder {

    public MedIntervalViewBuilder() {
    }

    public static List<MedDto> generateDtoFromEntity(Map<Med, IntakeInterval> medsIntervals) {

        List<MedDto> meds = new ArrayList<>();

        for (Map.Entry<Med, IntakeInterval> entry : medsIntervals.entrySet()) {
            Med med = entry.getKey();
            IntakeInterval intakeInterval = entry.getValue();

            IntakeIntervalDto intakeIntervalDto = new IntakeIntervalDto(intakeInterval.getId(), intakeInterval.getStartDate(), intakeInterval.getEndDate());
            MedDto medDto = new MedDto(med.getId(), med.getName(), med.getDosage(), med.getSideEffects(), intakeIntervalDto);

            meds.add(medDto);
        }

        return meds;
    }
}

