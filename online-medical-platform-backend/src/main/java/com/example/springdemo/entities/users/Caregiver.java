package com.example.springdemo.entities.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "caregiver")
@PrimaryKeyJoinColumn(name = "id")
public class Caregiver extends User {

    @OneToMany(mappedBy = "caregiver", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Patient> patients;

    public Caregiver(Long id, String email, String password, String firstName, String lastName, String birthDate, String gender, String address, String type) {
        super(id, email, password, firstName, lastName, birthDate, gender, address, type);
    }
}
