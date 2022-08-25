package com.innovation.myownblog.controller;

import com.innovation.myownblog.dto.SignupRequestDto;
import com.innovation.myownblog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignupRequestDto> signup(@RequestBody SignupRequestDto signupRequestDto) {     // UserDto를 파라미터로 받아
        return ResponseEntity.ok(userService.signup(signupRequestDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")         // @PreAuthorize을 통해 => USER, ADMIN 두 가지 권한 모두 호출할 수 있는 API가 됨
    public ResponseEntity<SignupRequestDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")         // @PreAuthorize으로 USER 권한만 호출
    public ResponseEntity<SignupRequestDto> getUserInfo(@PathVariable String username) {             // UserService에서 만들었던 username 파라미터를 기준으로,
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));             // 유저 정보와 권한 정보를 리턴하는 API
    }
}