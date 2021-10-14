package com.example.springdemo.repositories;

import com.example.springdemo.entities.MonitoredData;
import com.example.springdemo.entities.users.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonitoredDataRepo extends CrudRepository<MonitoredData, Long> {
    public List<MonitoredData> findByPatient(Patient patient);
    public Optional<MonitoredData> findById(long id);
}
