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
public class PostDetailPlan {
    private int day;
    private int sequence;
    @JsonProperty("attraction_id")
    private Long attractionId;
}
