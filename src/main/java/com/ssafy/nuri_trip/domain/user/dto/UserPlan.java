package com.ssafy.nuri_trip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserPlan {
    private Long id;
    private Long userId;
    private String title;
    private String image;
    private LocalDate startDate;
    private LocalDate endDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
}
