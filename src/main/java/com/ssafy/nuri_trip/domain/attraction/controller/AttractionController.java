package com.ssafy.nuri_trip.domain.attraction.controller;

import com.ssafy.nuri_trip.domain.attraction.dto.Attraction;
import com.ssafy.nuri_trip.domain.attraction.dto.AttractionDetail;
import com.ssafy.nuri_trip.domain.attraction.dto.Gugun;
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
    private final AttractionService service;

    @GetMapping("/{contentId}")
    public ResponseEntity<BaseResponse<?>> getAttractionById(@PathVariable int contentId) {
        try {
            AttractionDetail attraction = service.getAttractionById(contentId);
            return handleSuccess(attraction);
        } catch (BaseException e) {
            return handleException(e.getStatus());
        }
    }

    @GetMapping("/guguns")
    public ResponseEntity<BaseResponse<?>> getGuguns(@RequestParam("sido") int sidoCode){
        try{
            List<Gugun> guguns = service.getGuguns(sidoCode);
            return handleSuccess(guguns);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }

    /**
     * 특정 조건으로 관광지 조회
     */
//    @GetMapping
//    public ResponseEntity<BaseResponse<?>> getAttractionsByConditions(@RequestParam(required = false) Integer sido,
//                                                                       @RequestParam(required = false) Integer gugun,
//                                                                       @RequestParam(name="content", required = false) Integer contentTypeId) {
//        try {
//            List<Attraction> attractions = service.getAttractionsByConditions(sido, gugun, contentTypeId);
//            return handleSuccess(attractions);
//        } catch (BaseException e) {
//            return handleException(e.getStatus());
//        }
//    }

    /**
     * 무한 스크롤
     */
    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAttractions(@RequestParam(name="page_size") int pageSize,
                                                          @RequestParam(name="offset") int offset,
                                                          @RequestParam(required = false) Integer sido,
                                                          @RequestParam(required = false) Integer gugun,
                                                          @RequestParam(name="content", required = false) Integer contentTypeId) {
        try {
            List<Attraction> attractions = service.getAttractions(pageSize, offset, sido, gugun, contentTypeId);
            return handleSuccess(attractions);
        } catch (BaseException e) {
            return handleException(e.getStatus());
        }
    }
}
