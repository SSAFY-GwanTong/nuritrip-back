package com.ssafy.nuri_trip.domain.user.repository;

import com.ssafy.nuri_trip.domain.user.dto.CompletedMissions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMissionRepository {
    List<CompletedMissions> selectCompletedMissionsByUserId(Long userId);
    int updateStatus(Long userId, Long missionId, boolean status);
}
