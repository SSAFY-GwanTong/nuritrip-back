package com.ssafy.nuri_trip.domain.user.service;

import com.ssafy.nuri_trip.domain.user.dto.SignUpReq;
import com.ssafy.nuri_trip.domain.user.dto.User;
import com.ssafy.nuri_trip.domain.user.repository.UserRepository;
import com.ssafy.nuri_trip.global.common.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ssafy.nuri_trip.global.common.BaseResponseStatus.DATABASE_ERROR;
import static com.ssafy.nuri_trip.global.common.BaseResponseStatus.POST_USERS_EXISTS_ID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;

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
}
