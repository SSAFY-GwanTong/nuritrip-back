package com.ssafy.nuri_trip.domain.attraction.repository;

import com.ssafy.nuri_trip.domain.attraction.dto.Attraction;
import com.ssafy.nuri_trip.domain.attraction.dto.AttractionDetail;
import com.ssafy.nuri_trip.domain.attraction.dto.Gugun;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionRepository {
    AttractionDetail selectAttractionById(int contentId);
    List<Gugun> selectGugunBySidoCode(int sidoCode);
    //List<Attraction> selectAttractionsByConditions(Integer sido, Integer gugun, Integer contentTypeId);
    List<Attraction> selectAttractions(int pageSize, int offset, Integer sido, Integer gugun, Integer contentTypeId);
    List<Attraction> selectAttractionByIdList(List<Long> idList);
}
