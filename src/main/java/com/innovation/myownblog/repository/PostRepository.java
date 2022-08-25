package com.innovation.myownblog.repository;

import com.innovation.myownblog.entity.PostSummary;
import com.innovation.myownblog.entity.Post;
import com.innovation.myownblog.entity.PostDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<PostSummary> findAllByOrderByCreatedAtDesc(Class<PostSummary> postSumClass);
    PostDetail findById(Long id, Class<PostDetail> postDetailClass);
}