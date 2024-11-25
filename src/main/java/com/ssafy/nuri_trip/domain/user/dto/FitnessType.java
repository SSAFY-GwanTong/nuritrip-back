package com.ssafy.nuri_trip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FitnessType {
    private int id;
    private String name;
    private int level1;
    private int level2;
    private int level3;
    private int level4;
    private int level5;
}
