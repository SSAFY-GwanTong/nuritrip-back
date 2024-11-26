package com.ssafy.nuri_trip.domain.user.repository;

import com.ssafy.nuri_trip.domain.attraction.dto.Attraction;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLikeRepository {
    void like(@Param(value = "userId") Long userId, @Param("contentId") int contentId);
    void unlike(@Param("userId") Long userId, @Param("contentId") int contentId);
    boolean isLiked(@Param("userId") Long userId, @Param("contentId") int contentId);
    List<Attraction> selectAllLikedByUserId(@Param("userId") Long userId);
}
