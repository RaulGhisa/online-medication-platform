package com.example.springdemo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MonitoredDataDtoList {
    private List<MonitoredDataDto> monitoredData;
}
