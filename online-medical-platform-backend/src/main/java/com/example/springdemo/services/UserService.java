package com.example.springdemo.services;

import com.example.springdemo.dto.LoginDto;
import com.example.springdemo.dto.UserDto;
import com.example.springdemo.dto.UserViewDto;
import com.example.springdemo.dto.builders.EmptyMedicationBuilder;
import com.example.springdemo.dto.builders.UserBuilder;
import com.example.springdemo.dto.builders.UserViewBuilder;
import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.users.Caregiver;
import com.example.springdemo.entities.users.Patient;
import com.example.springdemo.entities.users.User;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.repositories.*;
import com.example.springdemo.validators.UserFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private final UserRepo userRepo;
    private final MedicationRepo medicationRepo;
    private final PatientRepo patientRepo;
    private final CaregiverRepo caregiverRepo;
    private final IntakeIntervalRepo intakeIntervalRepo;

    public UserService(UserRepo userRepo, MedicationRepo medicationRepo, PatientRepo patientRepo, CaregiverRepo caregiverRepo, IntakeIntervalRepo intakeIntervalRepo) {
        this.userRepo = userRepo;
        this.medicationRepo = medicationRepo;
        this.patientRepo = patientRepo;
        this.caregiverRepo = caregiverRepo;
        this.intakeIntervalRepo = intakeIntervalRepo;
    }

    public List<UserViewDto> findAll(String type) {
        List<User> users = (List<User>) userRepo.findAll();
        return users.stream()
                .filter((e) -> e.getType().equals(type))
                .map(UserViewBuilder::generateDtoFromEntity)
                .collect(Collectors.toList());
    }

    public UserViewDto findUserById(Long id) {
        Optional<User> user = userRepo.findById(id);

        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User", "user id", id);
        }
        return UserViewBuilder.generateDtoFromEntity(user.get());
    }

    public Long insert(UserDto userDto) {
        UserFieldValidator.validateInsertOrUpdate(userDto);

        Optional<User> userById = userRepo.findById(userDto.getId());
        Optional<User> userByEmail = userRepo.findUserByEmail(userDto.getEmail());

        if (userById.isPresent() || userByEmail.isPresent()) {
            return new Long(0);
        }

        if (userDto.getType().equals("patient")) {
            Long userId = patientRepo
                    .save((Patient) UserBuilder.generateEntityFromDto(userDto))
                    .getId();
            Patient patient = patientRepo.findById(userId).get();
            medicationRepo.save(EmptyMedicationBuilder.generateEntityFromDto(patient));
            return userId;
        } else {
            Long userId = caregiverRepo
                    .save((Caregiver) UserBuilder.generateEntityFromDto(userDto))
                    .getId();
            return userId;
        }
    }

    public Long delete(Long id) {
        Optional<User> userById = userRepo.findById(id);

        if (!userById.isPresent()) {
            return new Long(0);
        }

        User user = userById.get();

        if (user.getType().equals("patient")) {
            Medication medication = medicationRepo.findByPatient((Patient) user).get();
            List<IntakeInterval> intakeIntervals = medication.getIntakeIntervals();
            intakeIntervals.forEach((e) -> intakeIntervalRepo.delete(e));

            medicationRepo.delete(medication);
            patientRepo.delete((Patient) user);
        } else {
            Caregiver caregiver = (Caregiver) user;
            for (Patient patient : caregiver.getPatients()) {
                patient.setCaregiver(null);
                patientRepo.save(patient);
            }
            caregiver.getPatients().clear();
            caregiverRepo.delete(caregiver);
        }
        return id;
    }

    public Long update(UserDto userDto) {
        Optional<User> userById = userRepo.findById(userDto.getId());

        if (!userById.isPresent()) {
            return new Long(0);
        }

        User user = userById.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAddress(userDto.getAddress());
        user.setGender(userDto.getGender());

        return userRepo
                .save(user)
                .getId();
    }

    public UserViewDto login(LoginDto loginDto) {
        Optional<User> userByEmail = userRepo.findUserByEmail(loginDto.getEmail());

        if (!userByEmail.isPresent()) {
            return null;
        }

        User user = userByEmail.get();

        if (user.getPassword().equals(loginDto.getPassword())) {
            return new UserViewDto(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getBirthDate(),
                    user.getGender(),
                    user.getAddress(),
                    user.getType()
            );
        } else {
            return null;
        }
    }
}
