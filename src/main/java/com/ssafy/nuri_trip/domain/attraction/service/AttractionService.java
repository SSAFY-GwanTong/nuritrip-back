package com.ssafy.nuri_trip.domain.attraction.service;

import com.ssafy.nuri_trip.domain.attraction.dto.Attraction;
import com.ssafy.nuri_trip.domain.attraction.dto.AttractionDetail;
import com.ssafy.nuri_trip.domain.attraction.dto.Gugun;
import com.ssafy.nuri_trip.domain.attraction.repository.AttractionRepository;
import com.ssafy.nuri_trip.global.common.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ssafy.nuri_trip.global.common.BaseResponseStatus.ATTRACTION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AttractionService {
    private final AttractionRepository attractionRepo;

    public AttractionDetail getAttractionById(int contentId) throws BaseException {
        AttractionDetail attraction = attractionRepo.selectAttractionById(contentId);
        if (attraction == null) {
            throw new BaseException(ATTRACTION_NOT_FOUND);
        }
        return attraction;
    }

    public List<Gugun> getGuguns(int sidoCode) throws BaseException{
        List<Gugun> guguns = attractionRepo.selectGugunBySidoCode(sidoCode);
        return guguns;
    }


    public List<Attraction> getAttractionsByConditions(Integer sido, Integer gugun, Integer contentTypeId) throws BaseException {
        List<Attraction> attractions = attractionRepo.selectAttractionsByConditions(sido, gugun, contentTypeId);
        if (attractions == null || attractions.isEmpty()) {
            throw new BaseException(ATTRACTION_NOT_FOUND);
        }
        return attractions;
    }
}
