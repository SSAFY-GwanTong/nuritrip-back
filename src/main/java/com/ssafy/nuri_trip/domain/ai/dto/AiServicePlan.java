package com.ssafy.nuri_trip.domain.ai.dto;

import lombok.*;


public class AiServicePlan {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        long userPrimaryKey;
        int sidoCode;
        int duration;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        int i;
    }
}
