package com.ssafy.nuri_trip.domain.user.controller;

import com.ssafy.nuri_trip.domain.user.dto.SignUpReq;
import com.ssafy.nuri_trip.domain.user.service.UserService;
import com.ssafy.nuri_trip.global.common.BaseException;
import com.ssafy.nuri_trip.global.common.BaseResponse;
import com.ssafy.nuri_trip.global.controller.AbstractRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController extends AbstractRestController {

    private final UserService service;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<?>> signup(@RequestBody SignUpReq signUpReq){
        try{
            service.register(signUpReq);
            return handleSuccess(null);
        }catch(BaseException e){
            return handleException(e.getStatus());
        }
    }
}
