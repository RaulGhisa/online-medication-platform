package com.example.springdemo.repositories;

import com.example.springdemo.entities.users.Patient;
import com.example.springdemo.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    public Optional<User> findUserByEmail(String email);
}
