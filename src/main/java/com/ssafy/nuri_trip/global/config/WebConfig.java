package com.ssafy.nuri_trip.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.nuri_trip.global.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Override
    public void addInterceptors(InterceptorRegistry reg){
        reg.addInterceptor(new AuthenticationInterceptor(jwtService, objectMapper))
                .order(1)
                .addPathPatterns("/**")    //interceptor 작업이 필요한 path 모두 추가
                .excludePathPatterns("/api/auth/**", "/error","/api/attractions/**");
    }

}
