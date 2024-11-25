package com.ssafy.nuri_trip.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginReq {
    private String id;
    private String password;
}
