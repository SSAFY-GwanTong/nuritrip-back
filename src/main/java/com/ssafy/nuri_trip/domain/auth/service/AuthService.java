package com.ssafy.nuri_trip.domain.auth.service;

import com.ssafy.nuri_trip.domain.auth.dto.LoginReq;
import com.ssafy.nuri_trip.domain.auth.dto.LoginRes;
import com.ssafy.nuri_trip.domain.user.dto.User;
import com.ssafy.nuri_trip.domain.user.repository.UserRepository;
import com.ssafy.nuri_trip.global.common.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ssafy.nuri_trip.global.common.BaseResponseStatus.FAILED_TO_LOGIN;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;

    public LoginRes login(LoginReq loginReq) throws BaseException{

        String userId = loginReq.getId();
        User user = userRepo.selectByUserId(userId);
        if(loginReq.getPassword().equals(user.getPassword())){
            return new LoginRes(user.getId(), "jwt", user.getName(), user.getProfileImg(), user.getStrengthLevel());
        }else{
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }

}
