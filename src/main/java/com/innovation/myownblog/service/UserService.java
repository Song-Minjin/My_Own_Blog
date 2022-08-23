package com.innovation.myownblog.service;

import com.innovation.myownblog.dto.requestDto.SignupRequestDto;
import com.innovation.myownblog.model.User;
import com.innovation.myownblog.model.UserRoleEnum;
import com.innovation.myownblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(SignupRequestDto requestDto) {

        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> foundId = userRepository.findByUsername(username);
        if (foundId.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 비밀번호 일치 확인
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();
        if (!password.equals(passwordCheck)) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        // 닉네임 중복 확인
        String nickname = requestDto.getNickname();
        Optional<User> foundNickname = userRepository.findByUsername(nickname);
        if (foundNickname.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, nickname, role);
        userRepository.save(user);
    }
}