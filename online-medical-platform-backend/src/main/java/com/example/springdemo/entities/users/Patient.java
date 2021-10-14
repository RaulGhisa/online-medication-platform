package com.example.springdemo.entities.users;

import com.example.springdemo.entities.MedicalRecord;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.MonitoredData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "patient")
@PrimaryKeyJoinColumn(name = "id")
public class Patient extends User {

    @ManyToOne
    @JoinColumn(name = "caregiverId")
    private Caregiver caregiver;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private List<MonitoredData> monitoredData;

    public Patient() {
    }

    public Patient(Long id, String email, String password, String firstName, String lastName, String birthDate, String gender, String address, String type, Caregiver caregiver) {
        super(id, email, password, firstName, lastName, birthDate, gender, address, type);
        this.caregiver = caregiver;
    }
}
