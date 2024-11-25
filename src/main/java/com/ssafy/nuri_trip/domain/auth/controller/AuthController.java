package com.ssafy.nuri_trip.domain.auth.controller;

import com.ssafy.nuri_trip.domain.auth.dto.LoginReq;
import com.ssafy.nuri_trip.domain.auth.dto.LoginRes;
import com.ssafy.nuri_trip.domain.auth.service.AuthService;
import com.ssafy.nuri_trip.global.common.BaseException;
import com.ssafy.nuri_trip.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse<?>> signin(@RequestBody LoginReq loginReq){
        try{
            LoginRes loginRes = service.login(loginReq);
            return new ResponseEntity<>(new BaseResponse<>(loginRes), HttpStatus.OK);
        }catch(BaseException e){
            return new ResponseEntity<>(new BaseResponse<>(e.getStatus()), HttpStatus.valueOf(e.getStatus().getCode()));
        }
    }
}
