package com.innovation.myownblog.service;

import com.innovation.myownblog.dto.requestDto.PostRequestDto;
import com.innovation.myownblog.dto.responseDto.*;
import com.innovation.myownblog.model.Post;
import com.innovation.myownblog.model.PostDetail;
import com.innovation.myownblog.model.PostSum;
import com.innovation.myownblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public CreateResponse create(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new CreateResponse(post.getId());
    }

    public GetAllResponse get() {
        List<PostSum> postSum = postRepository.findAllByOrderByCreatedAtDesc(PostSum.class);
        return new GetAllResponse(postSum);
    }

    public GetOneResponse getOne(Long id) {
        PostDetail postDetail = postRepository.findById(id, PostDetail.class);
        return new GetOneResponse(postDetail);
    }

    public UpdateResponse update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return new UpdateResponse(post.getId());
    }

    public DeleteResponse delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        postRepository.deleteById(id);
        return new DeleteResponse(post.getId());
    }

    public CheckResponse checkPassword(@PathVariable Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        boolean password_receive = post.matchPassword(password);
        return new CheckResponse(password_receive);
    }
}