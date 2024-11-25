package com.ssafy.nuri_trip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetMissionsRes {
    private Long id;
    private String title;
    private int level;
    private List<String> fitnessTypes;
}
