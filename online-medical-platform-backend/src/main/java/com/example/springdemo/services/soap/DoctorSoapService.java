package com.example.springdemo.services.soap;

import activity.ActivityHistory;
import activity.ActivityRequest;
import activity.ObjectFactory;
import activity.RecommendationRequest;
import com.example.springdemo.entities.IntakeInterval;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.MonitoredData;
import com.example.springdemo.entities.users.Patient;
import com.example.springdemo.repositories.MedicationRepo;
import com.example.springdemo.repositories.MonitoredDataRepo;
import com.example.springdemo.repositories.PatientRepo;
import medication.MedicationHistory;
import medication.MedicationRequest;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorSoapService {

    private final PatientRepo patientRepo;
    private final MonitoredDataRepo monitoredDataRepo;
    private final MedicationRepo medicationRepo;

    public DoctorSoapService(PatientRepo patientRepo, MonitoredDataRepo monitoredDataRepo, MedicationRepo medicationRepo) {
        this.patientRepo = patientRepo;
        this.monitoredDataRepo = monitoredDataRepo;
        this.medicationRepo = medicationRepo;
    }

    public ActivityHistory getActivities(ActivityRequest activityRequest) {
        ActivityHistory activityHistory = new ActivityHistory();

        Patient patient = patientRepo.findById(activityRequest.getPatientId()).get();
        List<MonitoredData> monitoredData = patient.getMonitoredData();
        List<ActivityHistory.Activity> monitoredDataList = monitoredData.stream()
                .map(e -> {
                    ActivityHistory.Activity mData = new ObjectFactory().createActivityHistoryActivity();
                    mData.setActivityId(e.getId());
                    mData.setActivity(e.getActivity());
                    mData.setStartTime(convertToTimeDate(e.getStartTime()));
                    mData.setEndTime(convertToTimeDate(e.getEndTime()));
                    mData.setIsProblem(e.getIsProblem());
                    mData.setRecommendation(e.getRecommendation() != null ? e.getRecommendation() : "");

                    return mData;
                })
                .collect(Collectors.toList());

        activityHistory.setPatientId(activityRequest.getPatientId());
        activityHistory.getActivity().addAll(monitoredDataList);
        return activityHistory;
    }

    public void insertRecommendation(RecommendationRequest recommendationRequest) {

        MonitoredData data = monitoredDataRepo.findById(recommendationRequest.getMonitoredDataId()).get();
        data.setRecommendation(recommendationRequest.getRecommendation());

        monitoredDataRepo.save(data);
    }

    public MedicationHistory getMedication(MedicationRequest medicationRequest) {
        Patient patient = patientRepo.findById(medicationRequest.getPatientId()).get();

        List<IntakeInterval> intakeIntervals = medicationRepo.findByPatient(patient).get().getIntakeIntervals();

        MedicationHistory medicationHistory = new MedicationHistory();
        medicationHistory.setPatientId(medicationRequest.getPatientId());
        medicationHistory.getMedication().addAll(
                intakeIntervals.stream()
                .map(e -> {
                    MedicationHistory.Medication medication = new MedicationHistory.Medication();
                    medication.setTaken(e.getTaken());
                    medication.setDate(e.getTakenHour());
                    medication.setMedId(e.getId());
                    medication.setMedName(e.getMed().getName());

                    return medication;
                })
                .collect(Collectors.toList())
        );

        return medicationHistory;
    }


    private String convertToTimeDate(Long millis) {
        return new DateTime(millis, DateTimeZone.UTC).toLocalDateTime().toString();
    }
}
