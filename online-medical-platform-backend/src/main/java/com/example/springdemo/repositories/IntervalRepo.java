package com.example.springdemo.repositories;

import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Med;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntervalRepo extends CrudRepository<IntakeInterval, Long> {

}
