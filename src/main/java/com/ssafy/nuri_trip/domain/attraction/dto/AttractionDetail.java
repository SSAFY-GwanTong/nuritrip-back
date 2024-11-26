package com.ssafy.nuri_trip.domain.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AttractionDetail {
    private int contentId;
    private String title;
    private String img;
    private String tel;
    private String address1;
    private String address2;
    private String overview;
    private int likes;
}
