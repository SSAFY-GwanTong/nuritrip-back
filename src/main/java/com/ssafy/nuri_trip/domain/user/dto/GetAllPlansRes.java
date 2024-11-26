package com.ssafy.nuri_trip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class GetAllPlansRes {
    private Long id;
    private String title;
    private String image;
    private LocalDate startDate;
    private LocalDate endDate;
}
