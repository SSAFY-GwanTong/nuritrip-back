package com.ssafy.nuri_trip.domain.user.repository;

import com.ssafy.nuri_trip.domain.user.dto.CompletedMissions;
import com.ssafy.nuri_trip.domain.user.dto.Mission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMissionRepository {
    List<CompletedMissions> selectCompletedMissionsByUserId(Long userId);
    int updateStatus(Long userId, Long missionId, boolean status);
    Mission selectByUserPlanId(Long userPlanId, Long contentId);
    int selectMissionId(Long attractionId);
    int insert(Long userId, Long userPlanId, int missionId);
}
