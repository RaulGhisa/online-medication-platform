package com.example.springdemo.repositories;

import com.example.springdemo.entities.MedicalRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;

public interface RecordRepo extends CrudRepository<MedicalRecord, Long> {
}
