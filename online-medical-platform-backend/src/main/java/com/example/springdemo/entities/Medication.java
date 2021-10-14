package com.example.springdemo.entities;

import com.example.springdemo.entities.users.Patient;
import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @OneToMany(mappedBy = "medication", fetch = FetchType.EAGER)
    private List<IntakeInterval> intakeIntervals;

    public Medication(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
