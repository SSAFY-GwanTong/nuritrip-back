package com.ssafy.nuri_trip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Mission {
    private Long id;
    private String title;
    private boolean status;
    private int level;
    private List<String> fitnessTypes;
}
