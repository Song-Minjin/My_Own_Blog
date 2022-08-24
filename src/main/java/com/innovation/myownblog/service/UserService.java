package com.innovation.myownblog.service;

import java.util.Collections;

import com.innovation.myownblog.dto.UserDto;
import com.innovation.myownblog.entity.Authority;
import com.innovation.myownblog.entity.User;
import com.innovation.myownblog.repository.UserRepository;
import com.innovation.myownblog.util.SecurityUtil;
import com.innovation.myownblog.exception.DuplicateMemberException;
import com.innovation.myownblog.exception.NotFoundMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;        // UserRepository 주입받음
    private final PasswordEncoder passwordEncoder;      // PasswordEncoder 주입받음 (회원가입해서 DB로 전달하기 위해)

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {      // username이 DB에 존재하는지 확인
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()               // 존재하지 않으면 Authority와 User 정보를 생성
                .authorityName("ROLE_USER")                     // 권한 정보 만들고,
                .build();

        User user = User.builder()                              // 유저 정보를 만들어서
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return UserDto.from(userRepository.save(user));         // => UserRepository의 save 메소드를 통해 DB에 정보를 저장
    }

    // get유저, 권한 정보를 가져오는 두 가지 메소드
    @Transactional(readOnly = true)      // 1. username을 기준으로 정보를 가져옴
    public UserDto getUserWithAuthorities(String username) {   // username을 파라미터로 받아
        return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)     // 2. 현재 SecurityContext에 저장된 username의 정보만 가져옴
    public UserDto getMyUserWithAuthorities() {
        return UserDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }
}