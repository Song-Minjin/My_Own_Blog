package com.innovation.myownblog.repository;

import com.innovation.myownblog.entity.Post;
import com.innovation.myownblog.entity.PostSum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<PostSum> findAllByOrderByCreatedAtDesc(Class<PostSum> postSumClass);
}