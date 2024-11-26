package com.ssafy.nuri_trip.domain.ai.repository;

import com.ssafy.nuri_trip.domain.ai.dto.AiPlan;
import com.ssafy.nuri_trip.domain.ai.dto.AiPlanDetail;
import com.ssafy.nuri_trip.domain.ai.dto.PossibleAiPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AiPlanRepository {
    List<AiPlan> selectAllByLevelAndAreaCode(int level, int areaCode);
    List<PossibleAiPlan> selectPossiblePlanListByLevelAndAreaCode(int level, int areaCode);

    List<AiPlanDetail> selectAiPlanDetailListByAiPlanId(int aiPlanId,int day);
}
