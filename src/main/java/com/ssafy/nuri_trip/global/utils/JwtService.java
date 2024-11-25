package com.ssafy.nuri_trip.global.utils;

import com.ssafy.nuri_trip.domain.auth.dto.JwtDto;
import com.ssafy.nuri_trip.global.common.BaseException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Key;
import java.util.Date;

import static com.ssafy.nuri_trip.global.common.BaseResponseStatus.EMPTY_JWT;
import static com.ssafy.nuri_trip.global.common.BaseResponseStatus.INVALID_JWT;

@Service
public class JwtService {
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    /**
     * JWT 생성
     * @param userId
     * @return String
     */
    public String createJwt(Long id){
        Date now = new Date();
        String jwt =  Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("id", id)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*365)))
                .signWith(key)  //signature 부분
                .compact();
        return jwt;
    }

    /**
     * Header에서 Authorization으로 JWT 추출
     * @return String
     */
    public String getJwt() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            System.out.println("token: " + token);
        }

        return token;
    }

    /**
     * JWT에서 userId, role 추출
     * @return JwtDto
     * @throws BaseException
     */
    public JwtDto getUserInfo() throws BaseException{
        //1. JWT 추출
        String accessToken = getJwt();
        if(accessToken == null || accessToken.length() == 0){
            System.out.println(EMPTY_JWT.getMessage());
            throw new BaseException(EMPTY_JWT);
        }

        //2. JWT parsing
        Jws<Claims> claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(accessToken);
        } catch(ExpiredJwtException e){
            //토큰이 만료된 경우에 대한 처리
            System.out.println(e.getMessage());
            throw new BaseException(INVALID_JWT);
        } catch(MalformedJwtException e){
            //토큰이 유효하지 않거나 변조된 경우에 대한 처리
            System.out.println(e.getMessage());
            throw new BaseException(INVALID_JWT);
        } catch(SignatureException e){
            //서명이 올바르지 않은 경우
            throw new BaseException(INVALID_JWT);
        } catch(Exception e){
            System.out.println(e.getMessage());
            throw new BaseException(INVALID_JWT);
        }

        //3. userId 추출 후 반환
        Long id = claims.getBody().get("id", Long.class);
        JwtDto jwtDto = new JwtDto(id);
        return jwtDto;
    }

}
