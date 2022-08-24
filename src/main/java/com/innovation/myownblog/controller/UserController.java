package com.innovation.myownblog.controller;

import com.innovation.myownblog.dto.UserDto;
import com.innovation.myownblog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(
            @Valid @RequestBody UserDto userDto     // UserDto를 파라미터로 받아
    ) {
        return ResponseEntity.ok(userService.signup(userDto));      // UserService의 signup 메소드를 호출
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")         // @PreAuthorize을 통해 => USER, ADMIN 두 가지 권한 모두 호출할 수 있는 API가 됨
    public ResponseEntity<UserDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")         // @PreAuthorize으로 USER 권한만 호출
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username) {             // UserService에서 만들었던 username 파라미터를 기준으로,
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));             // 유저 정보와 권한 정보를 리턴하는 API
    }
}