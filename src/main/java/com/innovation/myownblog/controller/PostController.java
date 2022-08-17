package com.innovation.myownblog.controller;

import com.innovation.myownblog.dto.PasswordRequestDto;
import com.innovation.myownblog.entity.Post;
import com.innovation.myownblog.dto.PostRequestDto;
import com.innovation.myownblog.entity.PostSum;
import com.innovation.myownblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        return postService.create(requestDto);
    }

    @GetMapping("/api/posts")
    public List<PostSum> getPosts() {
        return postService.get();
    }

    @GetMapping("/api/posts/{id}")
    public Post getOnePost(@PathVariable Long id) {
        return postService.getOne(id);
    }

    @PatchMapping ("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {     // PathVariable : 경로에 있는 변수
        return postService.delete(id);
    }

    @PostMapping("/api/posts/{id}")
    public boolean checkPassword(@PathVariable Long id, @RequestBody PasswordRequestDto passwordRequestDto){
        String password = passwordRequestDto.getPassword();
        return postService.checkpw(id, password);
    }


}