package com.ssafy.nuri_trip.domain.attraction.controller;

import com.ssafy.nuri_trip.domain.attraction.dto.Attraction;
import com.ssafy.nuri_trip.domain.attraction.service.AttractionService;
import com.ssafy.nuri_trip.global.common.BaseException;
import com.ssafy.nuri_trip.global.common.BaseResponse;
import com.ssafy.nuri_trip.global.controller.AbstractRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attractions")
@RequiredArgsConstructor
public class AttractionController extends AbstractRestController {
    private final AttractionService attractionService;

    /**
     * 특정 관광지 조회
     */
    @GetMapping("/{contentId}")
    public ResponseEntity<BaseResponse<?>> getAttractionById(@PathVariable int contentId) {
        try {
            Attraction attraction = attractionService.getAttractionById(contentId);
            return handleSuccess(attraction);
        } catch (BaseException e) {
            return handleException(e.getStatus());
        }
    }

    /**
     * 구군 코드로 관광지 리스트 조회
     */
    @GetMapping("/gugun/{gugunCode}")
    public ResponseEntity<BaseResponse<?>> getAttractionsByGugunCode(@PathVariable int gugunCode) {
        try {
            List<Attraction> attractions = attractionService.getAttractionsByGugunCode(gugunCode);
            return handleSuccess(attractions);
        } catch (BaseException e) {
            return handleException(e.getStatus());
        }
    }

    /**
     * 특정 조건으로 관광지 조회
     */
    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAttractionsByConditions(@RequestParam(required = false) Integer sido,
                                                                       @RequestParam(required = false) Integer gugun,
                                                                       @RequestParam(required = false) Integer contentTypeId) {
        try {
            List<Attraction> attractions = attractionService.getAttractionsByConditions(sido, gugun, contentTypeId);
            return handleSuccess(attractions);
        } catch (BaseException e) {
            return handleException(e.getStatus());
        }
    }
}
