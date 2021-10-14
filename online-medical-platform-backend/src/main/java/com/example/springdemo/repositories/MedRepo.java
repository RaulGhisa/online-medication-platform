package com.example.springdemo.repositories;

import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Med;
import com.example.springdemo.entities.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MedRepo extends CrudRepository<Med, Long> {

    public Optional<Med> findById(Long id);
    public Optional<Med> findByIntakeIntervals(IntakeInterval intakeInterval);
}
