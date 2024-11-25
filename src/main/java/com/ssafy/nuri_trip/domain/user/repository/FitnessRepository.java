package com.ssafy.nuri_trip.domain.user.repository;

import com.ssafy.nuri_trip.domain.user.dto.Fitness;
import com.ssafy.nuri_trip.domain.user.dto.FitnessMeasurementReq;
import com.ssafy.nuri_trip.domain.user.dto.FitnessType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FitnessRepository {
    List<Fitness> selectFitnessByUserId(Long userId);
    FitnessType selectFitnessTypeById(int typeId);
    int insertFitnessMeasurement(FitnessMeasurementReq req);
    int updateFitnessMeasurement(FitnessMeasurementReq req);
}
