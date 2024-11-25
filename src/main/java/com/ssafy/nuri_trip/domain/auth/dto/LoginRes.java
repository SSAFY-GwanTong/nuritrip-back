package com.ssafy.nuri_trip.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRes {
    private Long id;
    private String jwtToken;
    private String name;
    private String profileImg;
    private Integer strengthLevel;
}
