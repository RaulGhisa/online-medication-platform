package com.example.springdemo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "med")
public class Med {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "sideEffect")
    private String sideEffects;

    @Column(name = "dosage")
    private String dosage;

    @OneToMany(mappedBy = "med", fetch = FetchType.LAZY)
    private List<IntakeInterval> intakeIntervals;

    @Override
    public String toString() {
        return "Med{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sideEffects='" + sideEffects + '\'' +
                ", dosage='" + dosage + '\'' +
                '}';
    }
}
