package com.ssafy.nuri_trip.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.nuri_trip.domain.auth.dto.JwtDto;
import com.ssafy.nuri_trip.global.common.BaseException;
import com.ssafy.nuri_trip.global.utils.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;    //자바 객체를 json으로 serialization

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        boolean check = checkAnnotation(handler, NoAuth.class);
        if(check) return true;
        try{
            if(request.getMethod().equals("OPTIONS")) {
                return true;
            }
            JwtDto jwtDto = jwtService.getUserInfo();
            Long userIdByJwt = jwtDto.getId();
            request.setAttribute("userId", userIdByJwt);
            System.out.println(userIdByJwt + ": 인증 성공");

        } catch(BaseException exception){

            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden

            String requestURI = request.getRequestURI();

            Map<String, String> map = new HashMap<>();
            //redirectURI는 로그인 후 다시 원래 페이지로 돌아가기 위함이다.
            map.put("requestURI", "/signin?redirectURI="+requestURI);
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            response.getWriter().write(json);

            return false;
        }
        return true;
    }
    private boolean checkAnnotation(Object handler, Class cls){
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(handlerMethod.getMethodAnnotation(cls)!=null){   //해당 annotation이 존재하면 true
            return true;
        }
        return false;
    }

}