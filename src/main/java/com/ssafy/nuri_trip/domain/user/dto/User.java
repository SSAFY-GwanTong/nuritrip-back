package com.ssafy.nuri_trip.domain.user.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private Integer strengthLevel;
    private Character gender;
    private Integer age;
    private String profileImg;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
}
