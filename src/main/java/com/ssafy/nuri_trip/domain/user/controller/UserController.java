package com.ssafy.nuri_trip.domain.user.controller;

import com.ssafy.nuri_trip.domain.user.dto.*;
import com.ssafy.nuri_trip.domain.user.service.UserService;
import com.ssafy.nuri_trip.global.common.BaseException;
import com.ssafy.nuri_trip.global.common.BaseResponse;
import com.ssafy.nuri_trip.global.config.NoAuth;
import com.ssafy.nuri_trip.global.controller.AbstractRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController extends AbstractRestController {

    private final UserService service;

    @PostMapping("/signup")
    @NoAuth
    public ResponseEntity<BaseResponse<?>> signup(@RequestBody SignUpReq signUpReq){
        try{
            service.register(signUpReq);
            return handleSuccess(null);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }

    @GetMapping("/my")
    public ResponseEntity<BaseResponse<?>> getUserInfo(@RequestAttribute("userId") Long userId){
        try{
            GetUserInfoRes user = service.getUserInfo(userId);
            return handleSuccess(user);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }

    /**
     * 유저 미션 관련 기능
     */
    @GetMapping("/my/missions")
    public ResponseEntity<BaseResponse<?>> getMissions(@RequestAttribute("userId") Long userId){
        try{
            List<GetMissionsRes> result = service.getCompletedMissions(userId);
            return handleSuccess(result);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }

    @PutMapping("/my/missions/{mission_id}/status/{status}")
    public ResponseEntity<BaseResponse<?>> updateMissionStatus(@RequestAttribute("userId") Long userId,
                                                               @PathVariable("mission_id") Long missionId,
                                                               @PathVariable("status") boolean status){
        try{
            service.updateMissionStatus(userId, missionId, status);
            return handleSuccess(null);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }

    /**
     * 유저 계획 관련 기능
     */
    @GetMapping("/my/plans")
    public ResponseEntity<BaseResponse<?>> getAllPlans(@RequestAttribute("userId") Long userId){
        try{
            List<GetAllPlansRes> result = service.getPlansByUserId(userId);
            return handleSuccess(result);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }

    }

    @GetMapping("my/plans/{user_plan_id}")
    public ResponseEntity<BaseResponse<?>> getPlan(@PathVariable("user_plan_id") Long userPlanId){
        try{
            List<DetailPlan> result = service.getPlanByUserPlanId(userPlanId);
            return handleSuccess(result);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }

    @PostMapping("my/plans")
    public ResponseEntity<BaseResponse<?>> registerPlan(@RequestAttribute("userId") Long userId,
                                                        @RequestBody RegisterPlanReq registerPlanReq){
        try{
            service.registerPlan(userId, registerPlanReq);
            return handleSuccess(null);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }

    /**
     * 체력 관련 기능
     */
    @GetMapping("/my/fitness")
    public ResponseEntity<BaseResponse<?>> getFitness(@RequestAttribute("userId") Long userId){
        try{
            List<Fitness> result = service.getFitness(userId);
            return handleSuccess(result);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }

    @PostMapping("/my/fitness-measurement")
    public ResponseEntity<BaseResponse<?>> postFitnessMeasurement(@RequestAttribute("userId") Long userId,
                                                                  @RequestBody FitnessMeasurementReq req){
        try{
            req.setUserId(userId);
            service.postFitnessMeasurement(req);
            return handleSuccess(null);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }

    @PatchMapping("/my/fitness-measurement")
    public ResponseEntity<BaseResponse<?>> updateFitnessMeasurement(@RequestAttribute("userId") Long userId,
                                                                  @RequestBody FitnessMeasurementReq req){
        try{
            req.setUserId(userId);
            service.updateFitnessMeasurement(req);
            return handleSuccess(null);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }
}
