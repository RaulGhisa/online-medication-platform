package com.example.springdemo.entities;


import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "intakeInterval")
public class IntakeInterval {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medicationId", nullable = false)
    private Medication medication;

    @ManyToOne
    @JoinColumn(name = "medId", nullable = false)
    private Med med;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "taken")
    private Boolean taken;

    @Column(name = "takenHour")
    private String takenHour;

    @Override
    public String toString() {
        return "IntakeInterval{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
