package com.example.springdemo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IntervalDto {
    private Long id;
    private String startDate;
    private String endDate;
    private Long medicationId;
    private Long medId;

}
