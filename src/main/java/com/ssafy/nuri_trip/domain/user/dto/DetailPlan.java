package com.ssafy.nuri_trip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DetailPlan {
    private Long userDetailPlanId;
    private int day;
    private int sequence;
    private Long contentId;
    private String attractionName;
    private String imgUrl;
    private String addr1;
    private String addr2;
    private String overview;
    private Mission mission;
}
