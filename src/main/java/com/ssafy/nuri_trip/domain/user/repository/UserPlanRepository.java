package com.ssafy.nuri_trip.domain.user.repository;

import com.ssafy.nuri_trip.domain.user.dto.GetPlansRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserPlanRepository {
    List<GetPlansRes> selectByUserId(Long userId);
}
