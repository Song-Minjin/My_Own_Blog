package com.innovation.myownblog.controller;

import com.innovation.myownblog.dto.LoginDto;
import com.innovation.myownblog.dto.TokenDto;
import com.innovation.myownblog.entity.RefreshToken;
import com.innovation.myownblog.jwt.JwtFilter;
import com.innovation.myownblog.jwt.TokenProvider;
import com.innovation.myownblog.repository.RefreshTokenRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, RefreshTokenRepository refreshTokenRepository) {
        this.tokenProvider = tokenProvider;                                          // TokenProvider 주입받음
        this.authenticationManagerBuilder = authenticationManagerBuilder;            // AuthenticationManageBuilder 주입받음
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @PostMapping("/login")           // 로그인 경로를 '/auth/login' + Post 요청으로 지정
    public ResponseEntity<TokenDto> authorize(@RequestBody LoginDto loginDto) {                // LoginDto로 username과 password를 파라미터로 받고,

        UsernamePasswordAuthenticationToken authenticationToken =                             // 이를 이용해 UsernamePasswordAuthenticationToken을 생성
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);     // authenticationToken을 이용하여 Authentication 객체를 생성하기 위해 authenticate 메소드가 실행이 될 때,
        SecurityContextHolder.getContext().setAuthentication(authentication);                                           // loadUserByUsername이 실행됨. 그렇게 나온 결과를 Authentication 객체로 생성하여 Security Context에 저장

        TokenDto tokenDto = tokenProvider.createToken(authentication);                           // 그렇게 만들어진 Authentication 객체를 createToken 메소드를 통해 JWT Token 생성

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        HttpHeaders httpHeaders = new HttpHeaders();                                            // JWT 토큰을 Response Header에도 넣어주고,
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDto);

        return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);             // TokenDto를 이용하여 Response Body에도 넣어 리턴함
    }
}
