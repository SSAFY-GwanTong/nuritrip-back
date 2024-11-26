package com.ssafy.nuri_trip.domain.attraction.repository;

import com.ssafy.nuri_trip.domain.attraction.dto.Attraction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AttractionRepository {
    /**
     * 특정 관광지를 ID로 조회
     */
    Attraction selectAttractionById(@Param("contentId") int contentId);

    /**
     * 구군 코드로 관광지 리스트 조회
     */
    List<Attraction> selectAttractionsByGugunCode(@Param("gugunCode") int gugunCode);

    /**
     * 특정 조건으로 관광지 조회
     */
    List<Attraction> selectAttractionsByConditions(@Param("sido") Integer sido,
                                                   @Param("gugun") Integer gugun,
                                                   @Param("contentTypeId") Integer contentTypeId);
}
