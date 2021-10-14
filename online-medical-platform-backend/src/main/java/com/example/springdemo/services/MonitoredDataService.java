package com.example.springdemo.services;

import com.example.springdemo.dto.MonitoredDataDto;
import com.example.springdemo.dto.MonitoredDataViewDto;
import com.example.springdemo.dto.Notification;
import com.example.springdemo.dto.SubscribeDto;
import com.example.springdemo.entities.MonitoredData;
import com.example.springdemo.entities.users.Caregiver;
import com.example.springdemo.entities.users.Patient;
import com.example.springdemo.repositories.CaregiverRepo;
import com.example.springdemo.repositories.MonitoredDataRepo;
import com.example.springdemo.repositories.PatientRepo;
import com.google.gson.Gson;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MonitoredDataService {

    private final MonitoredDataRepo monitoredDataRepo;
    private final PatientRepo patientRepo;
    private final CaregiverRepo caregiverRepo;
    private final SimpMessagingTemplate webSocket;
    private final Gson gson;

    private int state = 0;

    @Autowired
    public MonitoredDataService(MonitoredDataRepo monitoredDataRepo, PatientRepo patientRepo, CaregiverRepo caregiverRepo, SimpMessagingTemplate webSocket) {
        this.monitoredDataRepo = monitoredDataRepo;
        this.patientRepo = patientRepo;
        this.caregiverRepo = caregiverRepo;
        this.webSocket = webSocket;
        this.gson = new Gson();
    }

    public SubscribeDto subscribe(Long caregiverId) {
        return new SubscribeDto("success");
    }

    public void insert(MonitoredDataDto monitoredDataDto) {
        Patient patient;

        if (state == 0) {
            patient = patientRepo.findById(new Long(1970927)).get();
            state = 1;
        } else {
            patient = patientRepo.findById(new Long(666)).get();
            state = 0;
        }
        Caregiver caregiver = patient.getCaregiver();
        Boolean isProblem = true;
        if (caregiver != null) {
            switch (checkActivity(monitoredDataDto)) {
                case "sleeping":
                    webSocket.convertAndSend("/topic/problems/" + caregiver.getId(),
                            gson.toJson(new Notification(patient.getFirstName() + " " + patient.getLastName(), "sleeping problem")));
                    break;
                case "bathroom":
                    webSocket.convertAndSend("/topic/problems/" + caregiver.getId(),
                            gson.toJson(new Notification(patient.getFirstName() + " " + patient.getLastName(), "bathroom problem")));
                    break;
                case "leaving":
                    webSocket.convertAndSend("/topic/problems/" + caregiver.getId(),
                            gson.toJson(new Notification(patient.getFirstName() + " " + patient.getLastName(), "leaving problem")));
                    break;
                default:
                    isProblem = false;
            }
        }

        monitoredDataRepo.save(
                new MonitoredData(
                        patient,
                        monitoredDataDto.getStartTime(),
                        monitoredDataDto.getEndTime(),
                        monitoredDataDto.getActivityLabel(),
                        isProblem
                ));
    }

    private String checkActivity(MonitoredDataDto monitoredDataDto) {
        Long startTime = monitoredDataDto.getStartTime();
        Long endTime = monitoredDataDto.getEndTime();
        String activity = monitoredDataDto.getActivityLabel();
        int hours = toHours(endTime - startTime);

        if (activity.equalsIgnoreCase("sleeping")) {
            return hours > 12 ? "sleeping" : "";
        } else if (inBathroom(activity)) {
            return hours > 1 ? "bathroom" : "";
        } else if (activity.equalsIgnoreCase("leaving")) {
            return hours > 12 ? "leaving" : "";
        }
        return "";
    }

    private boolean inBathroom(String activity) {
        if (Stream.of("toileting", "showering", "grooming").anyMatch(activity::equalsIgnoreCase)) {
            return true;
        }
        return false;
    }

    private int toHours(Long millis) {
        Double hours = (double) (millis / 1000 / 3600);
        return hours.intValue();
    }

    public List<MonitoredDataViewDto> findAllByPatient(Long patientId) {
        Patient patient = patientRepo.findById(patientId).get();
        List<MonitoredData> monitoredData = monitoredDataRepo.findByPatient(patient);

        return monitoredData.stream()
                .map((e) -> new MonitoredDataViewDto(
                        convertToTimeDate(e.getStartTime()),
                        convertToTimeDate(e.getEndTime()),
                        e.getActivity()))
                .collect(Collectors.toList());
    }

    private String convertToTimeDate(Long millis) {
        return new DateTime(millis, DateTimeZone.UTC).toLocalDateTime().toString();
    }
}
