package com.example.springdemo.services;

import com.example.springdemo.dto.MedicalRecordDto;
import com.example.springdemo.entities.MedicalRecord;
import com.example.springdemo.entities.users.Patient;
import com.example.springdemo.repositories.PatientRepo;
import com.example.springdemo.repositories.RecordRepo;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    private final RecordRepo recordRepo;
    private final PatientRepo patientRepo;

    public RecordService(RecordRepo recordRepo, PatientRepo patientRepo) {
        this.recordRepo = recordRepo;
        this.patientRepo = patientRepo;
    }

    public Long insert(MedicalRecordDto medicalRecordDto) {

        Patient patient = patientRepo.findById(medicalRecordDto.getPatientId()).get();

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatient(patient);
        medicalRecord.setDisease(medicalRecordDto.getDisease());
        medicalRecord.setDoctor(medicalRecordDto.getDoctor());
        medicalRecord.setHospital(medicalRecordDto.getHospital());

        return recordRepo.save(medicalRecord).getId();
    }

    public Long delete(Long id) {

        MedicalRecord medicalRecord = recordRepo.findById(id).get();

        recordRepo.delete(medicalRecord);

        return id;
    }

    public Long update(MedicalRecordDto medicalRecordDto) {

        MedicalRecord medicalRecord = recordRepo.findById(medicalRecordDto.getId()).get();

        medicalRecord.setHospital(medicalRecordDto.getHospital());
        medicalRecord.setDoctor(medicalRecordDto.getDoctor());
        medicalRecord.setDisease(medicalRecordDto.getDisease());

        recordRepo.save(medicalRecord);

        return medicalRecordDto.getId();
    }
}
