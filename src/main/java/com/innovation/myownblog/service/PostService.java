package com.innovation.myownblog.service;

import com.innovation.myownblog.dto.PostRequestDto;
import com.innovation.myownblog.dto.TokenDto;
import com.innovation.myownblog.dto.responseDto.*;
import com.innovation.myownblog.entity.Post;
import com.innovation.myownblog.entity.PostDetail;
import com.innovation.myownblog.entity.PostSummary;
import com.innovation.myownblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public CreatePostResponseDto create(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new CreatePostResponseDto(post.getId());
    }

    public GetAllPostsResponseDto get() {
        List<PostSummary> postSum = postRepository.findAllByOrderByCreatedAtDesc(PostSummary.class);
        return new GetAllPostsResponseDto(postSum);
    }

    public GetOnePostResponseDto getOne(Long id) {
        PostDetail postDetail = postRepository.findById(id, PostDetail.class);
        return new GetOnePostResponseDto(postDetail);
    }

    public UpdatePostResponseDto update(Long id, PostRequestDto requestDto, TokenDto tokenDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return new UpdatePostResponseDto(post.getId());
    }

    public DeletePostResponseDto delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        postRepository.deleteById(id);
        return new DeletePostResponseDto(post.getId());
    }

    public CheckPasswordResponseDto checkPassword(@PathVariable Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        boolean password_receive = post.matchPassword(password);
        return new CheckPasswordResponseDto(password_receive);
    }
}