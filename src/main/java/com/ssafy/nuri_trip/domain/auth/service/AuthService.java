package com.ssafy.nuri_trip.domain.auth.service;

import com.ssafy.nuri_trip.domain.auth.dto.LoginReq;
import com.ssafy.nuri_trip.domain.auth.dto.LoginRes;
import com.ssafy.nuri_trip.domain.user.dto.User;
import com.ssafy.nuri_trip.domain.user.repository.UserRepository;
import com.ssafy.nuri_trip.global.common.BaseException;
import com.ssafy.nuri_trip.global.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ssafy.nuri_trip.global.common.BaseResponseStatus.FAILED_TO_LOGIN;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginRes login(LoginReq loginReq) throws BaseException{

        String userId = loginReq.getId();
        User user = userRepo.selectByUserId(userId);
        String encodedPassword = passwordEncoder.encode(loginReq.getPassword());
        System.out.println(loginReq.getPassword());
        System.out.println(encodedPassword);
        if(user!= null && encodedPassword.equals(user.getPassword())){
            Long id = user.getId();
            String jwtToken = jwtService.createJwt(id);
            return new LoginRes(user.getId(), jwtToken, user.getName(), user.getProfileImg(), user.getStrengthLevel());
        }else{
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }

}
