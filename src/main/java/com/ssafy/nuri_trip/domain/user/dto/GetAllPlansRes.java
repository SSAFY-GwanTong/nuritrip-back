package com.ssafy.nuri_trip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class GetAllPlansRes {
    private Long id;
    private String title;
    private String image;
    private LocalDate startDate;
    private LocalDate endDate;
}
