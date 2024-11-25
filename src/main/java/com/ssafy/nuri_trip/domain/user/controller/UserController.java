package com.ssafy.nuri_trip.domain.user.controller;

import com.ssafy.nuri_trip.domain.user.dto.GetMissionsRes;
import com.ssafy.nuri_trip.domain.user.dto.SignUpReq;
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

    @PatchMapping("/my/missions/{mission_id}/status/{status}")
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

}
