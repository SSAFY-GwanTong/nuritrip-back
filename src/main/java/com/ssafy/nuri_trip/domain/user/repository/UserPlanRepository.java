package com.ssafy.nuri_trip.domain.user.repository;

import com.ssafy.nuri_trip.domain.user.dto.DetailPlan;
import com.ssafy.nuri_trip.domain.user.dto.GetAllPlansRes;
import com.ssafy.nuri_trip.domain.user.dto.PostDetailPlan;
import com.ssafy.nuri_trip.domain.user.dto.PostPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserPlanRepository {
    List<GetAllPlansRes> selectByUserId(Long userId);
    List<DetailPlan> selectByUserPlanId(Long userPlanId);
    int insertPlan(PostPlan plan);
    int insertDetailPlans(Long userPlanId, List<PostDetailPlan> detailPlan);
}
