package com.innovation.myownblog.controller;

import com.innovation.myownblog.dto.CheckPasswordRequestDto;
import com.innovation.myownblog.dto.PostRequestDto;
import com.innovation.myownblog.dto.TokenDto;
import com.innovation.myownblog.dto.responseDto.*;
import com.innovation.myownblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public CreatePostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.create(requestDto);
    }

    // 전체 게시글 조회
    @GetMapping("/posts")
    public GetAllPostsResponseDto getPosts() {
        return postService.get();
    }

    // 한 게시글 조회
    @GetMapping("/posts/{id}")
    public GetOnePostResponseDto getOnePost(@PathVariable Long id) {
        return postService.getOne(id);
    }

    // 게시글 수정
    @PatchMapping ("/posts/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public UpdatePostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @RequestHeader TokenDto tokenDto) {

        return postService.update(id, requestDto, tokenDto);
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public DeletePostResponseDto deletePost(@PathVariable Long id) {     // PathVariable : 경로에 있는 변수
        return postService.delete(id);
    }

    // 게시글 비밀번호 확인
    @PostMapping("/posts/{id}")
    public CheckPasswordResponseDto checkPassword(@PathVariable Long id, @RequestBody CheckPasswordRequestDto checkPasswordRequestDto){
        String password = checkPasswordRequestDto.getPassword();
        return postService.checkPassword(id, password);
    }

    /* 참고자료 - 권한 부여하기
    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")         // @PreAuthorize으로 USER 권한만 호출
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username) {             // UserService에서 만들었던 username 파라미터를 기준으로,
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));             // 유저 정보와 권한 정보를 리턴하는 API
    }*/
}