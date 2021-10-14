package com.example.springdemo.dto.builders;

import com.example.springdemo.dto.UserDto;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.users.Patient;
import com.example.springdemo.entities.users.User;

public class EmptyMedicationBuilder {

    public EmptyMedicationBuilder() {
    }

    public static Medication generateEntityFromDto(Patient patient) {
        return new Medication(patient);
    }
}

