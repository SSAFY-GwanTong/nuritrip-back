package com.ssafy.nuri_trip.domain.user.repository;

import com.ssafy.nuri_trip.domain.user.dto.Fitness;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FitnessRepository {
    List<Fitness> selectFitnessByUserId(Long userId);
}
