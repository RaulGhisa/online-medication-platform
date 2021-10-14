package com.example.springdemo.repositories;

import com.example.springdemo.entities.users.Caregiver;
import com.example.springdemo.entities.users.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaregiverRepo extends CrudRepository<Caregiver, Long> {

    public Optional<Caregiver> findById(Long id);
    public Optional<Caregiver> findByPatients(Patient patient);
}
