package com.ssafy.nuri_trip.domain.user.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PostPlan {
    private Long id;
    private Long userId;
    private String title;
    private String image;
    private LocalDate startDate;
    private LocalDate endDate;
}
