package com.ssafy.nuri_trip.domain.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Attraction {
    private int contentId;       // 관광지 고유 ID
    private String title;        // 관광지 이름
    private String address;      // 관광지 주소
    private double latitude;     // 위도
    private double longitude;    // 경도
}
