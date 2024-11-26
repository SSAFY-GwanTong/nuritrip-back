package com.ssafy.nuri_trip.domain.ai.controller;

import com.ssafy.nuri_trip.domain.ai.dto.AiPlanDetail;
import com.ssafy.nuri_trip.domain.ai.dto.AiServicePlan;
import com.ssafy.nuri_trip.domain.ai.service.AiService;
import com.ssafy.nuri_trip.domain.attraction.dto.Attraction;
import com.ssafy.nuri_trip.global.common.BaseException;
import com.ssafy.nuri_trip.global.common.BaseResponse;
import java.util.List;
import javax.swing.JList;

import com.ssafy.nuri_trip.global.controller.AbstractRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController extends AbstractRestController {
    private final AiService service;
    /**
     * /api/ai/plans?sido={}&day={}
     */
//    기간
//    체력레벨
//            지역이랑 레벨에 맞는 일정이 있는데.. 그중에서 하나를 뽑고 랜덤 그중에서
//            (ai) 하루에 일정 몇개
//
//        retrun 어트렉션 전체
    @GetMapping("/plans")
    public ResponseEntity<?> getAiPlans(
            @RequestAttribute("userId") long userId,
            @RequestParam("sido") int sidoCode,
            @RequestParam("day") int day){
        System.out.println("userId = " + userId);
        System.out.println("sidoCode = " + sidoCode);
        System.out.println("day = " + day);
        AiServicePlan.Request serviceRequestDto = AiServicePlan.Request.builder()
                .userPrimaryKey(userId)
                .sidoCode(sidoCode)
                .duration(day)
                .build();
        //TODO controller ~ Service
        List<Attraction> aiPlans = service.getAiPlans(serviceRequestDto);
        return handleSuccess(aiPlans);

    }
}
