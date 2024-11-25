package com.ssafy.nuri_trip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserMission {
    private Long id;
    private boolean status;
    private Long userId;
    private Long userPlanId;
    private Long missionId;
}
