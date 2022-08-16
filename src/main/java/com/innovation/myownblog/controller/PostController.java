package com.innovation.myownblog.controller;

import com.innovation.myownblog.domain.Post;
import com.innovation.myownblog.domain.PostRepository;
import com.innovation.myownblog.domain.PostRequestDto;
import com.innovation.myownblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;    // final = 필수적인 것
    private final PostService postService;

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @GetMapping("/api/posts")
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @PatchMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {     // PathVariable : 경로에 있는 변수
        postRepository.deleteById(id);
        return id;
    }
}