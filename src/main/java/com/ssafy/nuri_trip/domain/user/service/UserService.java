package com.ssafy.nuri_trip.domain.user.service;

import com.ssafy.nuri_trip.domain.user.dto.*;
import com.ssafy.nuri_trip.domain.user.repository.FitnessRepository;
import com.ssafy.nuri_trip.domain.user.repository.UserMissionRepository;
import com.ssafy.nuri_trip.domain.user.repository.UserPlanRepository;
import com.ssafy.nuri_trip.domain.user.repository.UserRepository;
import com.ssafy.nuri_trip.global.common.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final UserPlanRepository userPlanRepo;
    private final FitnessRepository fitnessRepo;

    @Transactional
    public void register(SignUpReq signUpReq) throws BaseException{
        User findUserByUserId = userRepo.selectByUserId(signUpReq.getUserId());
        if(findUserByUserId == null){
            String encodedPassword = passwordEncoder.encode(signUpReq.getPassword());
            User user = User.builder()
                    .userId(signUpReq.getUserId())
                    .password(encodedPassword)
                    .name(signUpReq.getName())
                    .age(signUpReq.getAge())
                    .gender(signUpReq.getGender())
                    .build();
            int res = userRepo.insert(user);
            fitnessRepo.insertDefaultFitness(user.getId());
            if(res != 1) throw new BaseException(DATABASE_ERROR);
        }else{
            throw new BaseException(POST_USERS_EXISTS_ID);
        }
    }

    public GetUserInfoRes getUserInfo(Long userId) throws BaseException{
        User user = userRepo.selectById(userId);
        GetUserInfoRes userInfo = GetUserInfoRes.builder()
                                                .id(user.getId())
                                                .name(user.getName())
                                                .profileImg(user.getProfileImg())
                                                .strengthLevel(user.getStrengthLevel())
                                                .build();
        return userInfo;
    }

    /**
     * 미션 관련 기능
     */
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

    /**
     * 계획 관련 기능
     */
    public List<GetAllPlansRes> getPlansByUserId(Long userId) throws BaseException{
        List<GetAllPlansRes> plans = userPlanRepo.selectByUserId(userId);
        return plans;
    }

    public List<DetailPlan> getPlanByUserPlanId(Long userPlanId) throws BaseException{
        List<DetailPlan> plan = userPlanRepo.selectByUserPlanId(userPlanId);
        for(DetailPlan detailPlan : plan){
            Long contentId = detailPlan.getContentId();
            Mission mission = userMissionRepo.selectByUserPlanId(userPlanId, contentId);
            detailPlan.setMission(mission);
        }
        return plan;
    }
    @Transactional
    public void registerPlan(Long userId, RegisterPlanReq registerPlanReq) throws BaseException{
        PostPlan plan = PostPlan.builder()
                                .userId(userId)
                                .title(registerPlanReq.getTitle())
                                .image(registerPlanReq.getImage())
                                .endDate(registerPlanReq.getEndDate())
                                .startDate(registerPlanReq.getStartDate())
                                .build();
        userPlanRepo.insertPlan(plan);
        Long userPlanId = plan.getId();
        int res = userPlanRepo.insertDetailPlans(userPlanId, registerPlanReq.getDetailPlan());
        System.out.println("detail plan : " + res + "개 들어감");
    }

    public List<Fitness> getFitness(Long userId) throws BaseException{
        List<Fitness> res = fitnessRepo.selectFitnessByUserId(userId);
        return res;
    }

    public void postFitnessMeasurement(FitnessMeasurementReq req) throws BaseException{
        FitnessType type = fitnessRepo.selectFitnessTypeById(req.getFitnessTypeId());
        int value = req.getValue();
        if(value < type.getLevel1()){
            req.setLevel(1);
        }else if(value<type.getLevel2()){
            req.setLevel(2);
        }else if(value<type.getLevel3()){
            req.setLevel(3);
        }else if(value<type.getLevel4()){
            req.setLevel(4);
        }else{
            req.setLevel(5);
        }
        fitnessRepo.insertFitnessMeasurement(req);
    }

    public void updateFitnessMeasurement(FitnessMeasurementReq req) throws BaseException{
        FitnessType type = fitnessRepo.selectFitnessTypeById(req.getFitnessTypeId());
        int value = req.getValue();
        if(value < type.getLevel1()){
            req.setLevel(1);
        }else if(value<type.getLevel2()){
            req.setLevel(2);
        }else if(value<type.getLevel3()){
            req.setLevel(3);
        }else if(value<type.getLevel4()){
            req.setLevel(4);
        }else{
            req.setLevel(5);
        }
        fitnessRepo.updateFitnessMeasurement(req);
    }

}
