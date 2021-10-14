package com.example.springdemo.entities;

import com.example.springdemo.entities.users.Patient;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "monitoredData")
public class MonitoredData {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    @Column(name = "startTime")
    private Long startTime;

    @Column(name = "endTime")
    private Long endTime;

    @Column(name = "activity")
    private String activity;

    @Column(name = "isProblem")
    private Boolean isProblem;

    @Column(name = "recommendation")
    private String recommendation;

    public MonitoredData(Patient patient, Long startTime, Long endTime, String activity, Boolean isProblem) {
        this.patient = patient;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
        this.isProblem = isProblem;
    }

    @Override
    public String toString() {
        return "MonitoredData{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", activity='" + activity + '\'' +
                '}';
    }
}
