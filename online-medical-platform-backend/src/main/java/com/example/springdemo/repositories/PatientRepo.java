package com.example.springdemo.repositories;

import com.example.springdemo.entities.users.Caregiver;
import com.example.springdemo.entities.users.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PatientRepo extends CrudRepository<Patient, Long> {

    public Optional<Patient> findById(Long id);
}
