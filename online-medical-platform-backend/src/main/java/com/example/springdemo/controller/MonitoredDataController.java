package com.example.springdemo.controller;

import com.example.springdemo.dto.MonitoredDataDto;
import com.example.springdemo.dto.MonitoredDataViewDto;
import com.example.springdemo.dto.SubscribeDto;
import com.example.springdemo.services.MonitoredDataService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Controller
public class MonitoredDataController {
    private final MonitoredDataService monitoredDataService;
    private Gson gson;

    @Autowired
    public MonitoredDataController(MonitoredDataService monitoredDataService) throws IOException, TimeoutException {
        this.monitoredDataService = monitoredDataService;
        gson = new Gson();
    }

    @GetMapping(value = "/monitored-data/all/{patientId}")
    public List<MonitoredDataViewDto> findAllByPatient(@PathVariable Long patientId) {
        return monitoredDataService.findAllByPatient(patientId);
    }


    @RabbitListener(queues = {"add-monitored-data"})
    private void insertMonitoredData(byte[] message) {
        String stringedMessage = new String(message);
        MonitoredDataDto monitoredDataDto = gson.fromJson(stringedMessage, MonitoredDataDto.class);
        System.out.println(stringedMessage);
        monitoredDataService.insert(monitoredDataDto);
    }

    @MessageMapping("/caregiver/{caregiverId}")
    @SendTo("/topic/problems/{caregiverId}")
    private SubscribeDto getNotified(@DestinationVariable Long caregiverId, @Payload String message) throws InterruptedException {
        return monitoredDataService.subscribe(caregiverId);
    }

}
