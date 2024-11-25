package com.ssafy.nuri_trip.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class FitnessMeasurementReq {
    private Long userId;
    @JsonProperty("fitness_type_id")
    private int fitnessTypeId;
    private int value;
    private int level;
}
