package com.ssafy.nuri_trip.domain.ai.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AiPlanDetail {
    long id;
    int day;
    int sequence;
    long aiPlanId;
    long attractionId;
    String content;
}
