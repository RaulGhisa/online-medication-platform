package com.example.springdemo.services;

import com.example.springdemo.dto.MedViewDto;
import com.example.springdemo.entities.Med;
import com.example.springdemo.repositories.MedRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedService {

    private final MedRepo medRepo;

    public MedService(MedRepo medRepo) {
        this.medRepo = medRepo;
    }

    public List<MedViewDto> findAll() {
        List<Med> meds = (List<Med>) medRepo.findAll();

        List<MedViewDto> medsViewDto = meds.stream()
                .map((e) -> new MedViewDto(e.getId(), e.getName(), e.getDosage(), e.getSideEffects()))
                .collect(Collectors.toList());

        return medsViewDto;
    }
}
