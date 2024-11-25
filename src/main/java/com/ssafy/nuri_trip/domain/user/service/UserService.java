package com.ssafy.nuri_trip.domain.user.service;

import com.ssafy.nuri_trip.domain.user.dto.CompletedMissions;
import com.ssafy.nuri_trip.domain.user.dto.GetMissionsRes;
import com.ssafy.nuri_trip.domain.user.dto.SignUpReq;
import com.ssafy.nuri_trip.domain.user.dto.User;
import com.ssafy.nuri_trip.domain.user.repository.UserMissionRepository;
import com.ssafy.nuri_trip.domain.user.repository.UserRepository;
import com.ssafy.nuri_trip.global.common.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ssafy.nuri_trip.global.common.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final UserMissionRepository userMissionRepo;

    public void register(SignUpReq signUpReq) throws BaseException{
        User findUserByUserId = userRepo.selectByUserId(signUpReq.getUserId());
        if(findUserByUserId == null){
            String encodedPassword = passwordEncoder.encode(signUpReq.getPassword());
            User user = User.builder()
                    .userId(signUpReq.getUserId())
                    .password(encodedPassword)
                    .name(signUpReq.getName())
                    .age(signUpReq.getAge())
                    .build();
            int res = userRepo.insert(user);
            if(res != 1) throw new BaseException(DATABASE_ERROR);
        }else{
            throw new BaseException(POST_USERS_EXISTS_ID);
        }
    }

    public List<GetMissionsRes> getCompletedMissions(Long userId) throws BaseException{
        List<CompletedMissions> missions = userMissionRepo.selectCompletedMissionsByUserId(userId);
        List<GetMissionsRes> results = new ArrayList<>();
        for(CompletedMissions mission: missions){
            GetMissionsRes getMissionsRes = new GetMissionsRes(mission.getId(), mission.getTitle(), mission.getLevel(), new ArrayList<>());
            String fitnessTypes = mission.getFitnessTypes();
            if (fitnessTypes != null) {
                getMissionsRes.setFitnessTypes(Arrays.asList(fitnessTypes.split(",")));
            }
            results.add(getMissionsRes);
        }
        return results;
    }

    public void updateMissionStatus(Long userId, Long missionId, boolean status) throws BaseException{
        int res = userMissionRepo.updateStatus(userId, missionId, status);
        if(res != 1){
            throw new BaseException(MISSION_NOT_FOUND);
        }
    }
}
