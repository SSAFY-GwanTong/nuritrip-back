package com.ssafy.nuri_trip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompletedMissions {
    private Long id;
    private String title;
    private int level;
    private String fitnessTypes;
}
