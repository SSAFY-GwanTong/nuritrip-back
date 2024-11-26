package com.ssafy.nuri_trip.domain.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Attraction {
    private int contentId;
    private double latitude;
    private double longitude;
    private String title;
    private String img;
    private String address1;
    private String address2;
    private String contentType;
    private boolean isLiked;
}
