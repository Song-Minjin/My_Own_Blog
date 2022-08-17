package com.innovation.myownblog.service;

import com.innovation.myownblog.entity.Post;
import com.innovation.myownblog.entity.PostSum;
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
    public List<PostSum> get() {
        List<PostSum> postSum = postRepository.findAllByOrderByCreatedAtDesc(PostSum.class);
        return postSum;
    }

    @Transactional
    public Post getOne(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }

    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return post.getId();
    }

    @Transactional
    public Long delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        postRepository.deleteById(id);
        return post.getId();
    }

    @Transactional
    public boolean checkpw(@PathVariable Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return post.matchPassword(password);
    }
}