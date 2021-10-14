package com.example.springdemo.services;

import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Med;
import com.example.springdemo.entities.MedicalRecord;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.users.Patient;
import com.example.springdemo.repositories.IntakeIntervalRepo;
import com.example.springdemo.repositories.MedRepo;
import com.example.springdemo.repositories.MedicationRepo;
import com.example.springdemo.repositories.PatientRepo;
import com.example.springdemo.rpc.MedicationGrpc;
import com.example.springdemo.rpc.RpcMedication;
import io.grpc.Grpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RpcMedicationService extends MedicationGrpc.MedicationImplBase {

    private final PatientRepo patientRepo;
    private final MedRepo medRepo;
    private final IntakeIntervalRepo intakeIntervalRepo;
    private final MedicationRepo medicationRepo;

    public RpcMedicationService(PatientRepo patientRepo, MedRepo medRepo, IntakeIntervalRepo intakeIntervalRepo, MedicationRepo medicationRepo) {
        this.patientRepo = patientRepo;
        this.medRepo = medRepo;
        this.intakeIntervalRepo = intakeIntervalRepo;
        this.medicationRepo = medicationRepo;
    }

    public void startServer() throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8081)
                .addService(this)
                .build();
        server.start();

        System.out.println("Server started on " + server.getPort());

        server.awaitTermination();
    }

    @Override
    public void getMedicationPlan(RpcMedication.Patient patient, StreamObserver<RpcMedication.Med> responseObserver) {
        Patient patientDb = patientRepo.findById(patient.getPatientId()).get();
        Medication medication = medicationRepo.findByPatient(patientDb).get();

        List<IntakeInterval> intakeIntervals = intakeIntervalRepo.findByMedication(medication);
        intakeIntervals.stream()
                .map(e -> RpcMedication.Med.newBuilder()
                        .setStartHour(e.getStartDate())
                        .setEndHour(e.getEndDate())
                        .setMedName(e.getMed().getName())
                        .build())
                .forEach(responseObserver::onNext);

        responseObserver.onCompleted();
    }

    @Override
    public void sendMedicationTaken(RpcMedication.Taken taken, StreamObserver<RpcMedication.Empty> streamObserver) {
        printFormattedResponse(taken);

        Patient patient = patientRepo.findById(taken.getPatientId()).get();
        Medication medication = medicationRepo.findByPatient(patient).get();

        List<IntakeInterval> intakeIntervals = intakeIntervalRepo.findByMedication(medication);

        intakeIntervals.forEach(e -> {
            if (e.getMed().getName().equals(taken.getMedName())) {
                e.setTaken(taken.getIsTaken());
                intakeIntervalRepo.save(e);
            }
        });

        streamObserver.onNext(RpcMedication.Empty.newBuilder().build());
        streamObserver.onCompleted();
    }

    private void printFormattedResponse(RpcMedication.Taken taken) {
        System.out.println("Patient with ID: " + taken.getPatientId()
                + " did " + (taken.getIsTaken() ? "take" : "not take")
                + " his " + taken.getMedName());
    }
}