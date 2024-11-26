package com.ssafy.nuri_trip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetUserInfoRes {
    private Long id;
    private String name;
    private String profileImg;
    private int strengthLevel;
}
