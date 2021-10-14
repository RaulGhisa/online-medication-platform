package com.example.springdemo.controller.soap;

import activity.ActivityHistory;
import activity.ActivityRequest;
import activity.RecommendationRequest;
import com.example.springdemo.services.soap.DoctorSoapService;
import medication.MedicationHistory;
import medication.MedicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class DoctorSoapEndpoint {

    private final DoctorSoapService doctorSoapService;

    @Autowired
    public DoctorSoapEndpoint(DoctorSoapService doctorSoapService) {
        this.doctorSoapService = doctorSoapService;
    }

    @PayloadRoot(namespace = "activity", localPart = "ActivityRequest")
    @ResponsePayload
    public ActivityHistory getActivities(@RequestPayload ActivityRequest activityRequest) {
        return doctorSoapService.getActivities(activityRequest);
    }

    @PayloadRoot(namespace = "activity", localPart = "RecommendationRequest")
    @ResponsePayload
    public void insertRecommendation(@RequestPayload RecommendationRequest recommendationRequest) {
        doctorSoapService.insertRecommendation(recommendationRequest);
    }

    @PayloadRoot(namespace = "medication", localPart = "MedicationRequest")
    @ResponsePayload
    public MedicationHistory getMedication(@RequestPayload MedicationRequest medicationRequest) {
        return doctorSoapService.getMedication(medicationRequest);
    }
}
