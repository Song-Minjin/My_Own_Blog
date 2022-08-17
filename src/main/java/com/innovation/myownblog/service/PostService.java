package com.innovation.myownblog.service;

import com.innovation.myownblog.entity.Post;
import com.innovation.myownblog.entity.PostDetail;
import com.innovation.myownblog.entity.PostSum;
import com.innovation.myownblog.entity.jsonResponse.*;
import com.innovation.myownblog.repository.PostRepository;
import com.innovation.myownblog.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post create(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @Transactional
    public GetAllResponse get() {
        List<PostSum> postSum = postRepository.findAllByOrderByCreatedAtDesc(PostSum.class);
        return new GetAllResponse(postSum);
    }

    @Transactional
    public GetOneResponse getOne(Long id) {
        PostDetail postDetail = postRepository.findById(id, PostDetail.class);
        return new GetOneResponse(postDetail);
    }

    @Transactional
    public UpdateResponse update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return new UpdateResponse(post.getId());
    }

    @Transactional
    public DeleteResponse delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        postRepository.deleteById(id);
        return new DeleteResponse(post.getId());
    }

    @Transactional
    public CheckResponse checkpw(@PathVariable Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        boolean password_receive = post.matchPassword(password);
        return new CheckResponse(password_receive);
    }
}