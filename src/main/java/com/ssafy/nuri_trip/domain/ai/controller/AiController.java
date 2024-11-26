package com.ssafy.nuri_trip.domain.ai.controller;

import com.ssafy.nuri_trip.domain.ai.dto.AiPlanDetail;
import com.ssafy.nuri_trip.domain.ai.service.AiService;
import com.ssafy.nuri_trip.global.common.BaseException;
import com.ssafy.nuri_trip.global.common.BaseResponse;
import java.util.List;
import javax.swing.JList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {
    private final AiService service;
    /**
     * /api/ai/plans?sido={}&day={}
     */
    @GetMapping("/plans")
    public ResponseEntity<BaseResponse<?>> getAiPlans(@RequestParam("sido") int sidoCode,
                                                      @RequestParam("day") int day){
        try{
            List<AiPlanDetail> res = service.getAiPlans(sidoCode, day);
            return handleSuccess(res);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }
}
