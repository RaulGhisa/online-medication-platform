package com.example.springdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MonitoredDataDto {
    private Long startTime;
    private Long endTime;
    private String activityLabel;
}
