package com.example.springdemo.repositories;

import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Med;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.users.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface MedicationRepo extends CrudRepository<Medication, Long> {

    public Optional<Medication> findById(Long id);
    public Optional<Medication> findByPatient(Patient patient);
}
