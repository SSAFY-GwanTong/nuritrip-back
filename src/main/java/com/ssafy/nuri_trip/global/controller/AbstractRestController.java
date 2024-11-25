package com.ssafy.nuri_trip.global.controller;

import com.ssafy.nuri_trip.global.common.BaseResponse;
import com.ssafy.nuri_trip.global.common.BaseResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractRestController {
    protected ResponseEntity<BaseResponse<?>> handleSuccess(Object result){
        return new ResponseEntity<>(new BaseResponse<>(result), HttpStatus.OK);
    }

    protected ResponseEntity<BaseResponse<?>> handleException(BaseResponseStatus status){
        return new ResponseEntity<>(new BaseResponse<>(status), HttpStatus.valueOf(status.getCode()));
    }
}
