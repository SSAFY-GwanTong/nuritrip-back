package com.ssafy.nuri_trip.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpReq {
    @JsonProperty("user_id")
    private String userId;
    private String password;
    private String name;
    private int age;
}
