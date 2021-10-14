package com.example.springdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MonitoredDataViewDto {
    private String startTime;
    private String endTime;
    private String activityLabel;
}
