package com.innovation.myownblog.controller;

import com.innovation.myownblog.dto.PasswordRequestDto;
import com.innovation.myownblog.dto.PostRequestDto;
import com.innovation.myownblog.entity.jsonResponse.*;
import com.innovation.myownblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public CreateResponse createPost(@RequestBody PostRequestDto requestDto) {
        return postService.create(requestDto);
    }

    @GetMapping("/api/posts")
    public GetAllResponse getPosts() {
        return postService.get();
    }

    @GetMapping("/api/posts/{id}")
    public GetOneResponse getOnePost(@PathVariable Long id) {
        return postService.getOne(id);
    }

    @PatchMapping ("/api/posts/{id}")
    public UpdateResponse updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public DeleteResponse deletePost(@PathVariable Long id) {     // PathVariable : 경로에 있는 변수
        return postService.delete(id);
    }

    @PostMapping("/api/posts/{id}")
    public CheckResponse checkPassword(@PathVariable Long id, @RequestBody PasswordRequestDto passwordRequestDto){
        String password = passwordRequestDto.getPassword();
        return postService.checkpw(id, password);
    }


}