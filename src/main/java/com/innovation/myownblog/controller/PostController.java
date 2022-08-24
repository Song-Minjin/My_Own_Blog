package com.innovation.myownblog.controller;

import com.innovation.myownblog.dto.requestDto.CheckPasswordRequestDto;
import com.innovation.myownblog.dto.requestDto.PostRequestDto;
import com.innovation.myownblog.dto.responseDto.*;
import com.innovation.myownblog.security.UserDetailsImpl;
import com.innovation.myownblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/api/posts")
    public CreateResponse createPost(@RequestBody PostRequestDto requestDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 된 상태의 회원 테이블 ID (고유번호)
        Long userId = userDetails.getUser().getId();
        String userNickname = userDetails.getUser().getNickname();
        CreateResponse createResponse = postService.create(requestDto, userId, userNickname);
        return createResponse;
    }

    // 전체 게시글 조회
    @GetMapping("/api/posts")
    public GetAllResponse getPosts() {
        return postService.get();
    }

    // 게시글 한 개 조회
    @GetMapping("/api/posts/{id}")
    public GetOneResponse getOnePost(@PathVariable Long id) {
        return postService.getOne(id);
    }

    // 게시글 수정
    @PatchMapping ("/api/posts/{id}")
    public UpdateResponse updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public DeleteResponse deletePost(@PathVariable Long id) {     // PathVariable : 경로에 있는 변수
        return postService.delete(id);
    }

    // 게시글 비밀번호 확인
    @PostMapping("/api/posts/{id}")
    public CheckResponse checkPassword(@PathVariable Long id, @RequestBody CheckPasswordRequestDto checkPasswordRequestDto){
        String password = checkPasswordRequestDto.getPassword();
        return postService.checkPassword(id, password);
    }


}