package com.ssafy.nuri_trip.domain.attraction.service;

import com.ssafy.nuri_trip.domain.attraction.dto.Attraction;
import com.ssafy.nuri_trip.domain.attraction.repository.AttractionRepository;
import com.ssafy.nuri_trip.global.common.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ssafy.nuri_trip.global.common.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class AttractionService {
    private final AttractionRepository attractionRepository;

    /**
     * 특정 관광지를 ID로 조회
     */
    public Attraction getAttractionById(int contentId) throws BaseException {
        Attraction attraction = attractionRepository.selectAttractionById(contentId);
        if (attraction == null) {
            throw new BaseException(ATTRACTION_NOT_FOUND);
        }
        return attraction;
    }

    /**
     * 구군 코드로 관광지 리스트 조회
     */
    public List<Attraction> getAttractionsByGugunCode(int gugunCode) throws BaseException {
        List<Attraction> attractions = attractionRepository.selectAttractionsByGugunCode(gugunCode);
        if (attractions == null || attractions.isEmpty()) {
            throw new BaseException(ATTRACTION_NOT_FOUND);
        }
        return attractions;
    }

    /**
     * 특정 조건으로 관광지 조회
     */
    public List<Attraction> getAttractionsByConditions(Integer sido, Integer gugun, Integer contentTypeId) throws BaseException {
        List<Attraction> attractions = attractionRepository.selectAttractionsByConditions(sido, gugun, contentTypeId);
        if (attractions == null || attractions.isEmpty()) {
            throw new BaseException(ATTRACTION_NOT_FOUND);
        }
        return attractions;
    }
}
