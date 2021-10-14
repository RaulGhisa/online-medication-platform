package com.example.springdemo.repositories;

import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Med;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.users.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.imageio.stream.IIOByteBuffer;
import java.util.List;
import java.util.Optional;

@Repository
public interface IntakeIntervalRepo extends CrudRepository<IntakeInterval, Long> {

    public Optional<IntakeInterval> findById(Long id);
    public List<IntakeInterval> findByMedication(Medication medication);
}
