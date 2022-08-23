package com.innovation.myownblog.repository;

import com.innovation.myownblog.model.Post;
import com.innovation.myownblog.model.PostDetail;
import com.innovation.myownblog.model.PostSum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
//    <T> List<T> findAllByOrderByCreatedAtDesc(Class<T> type);
    List<PostSum> findAllByOrderByCreatedAtDesc(Class<PostSum> postSumClass);
    PostDetail findById(Long id, Class<PostDetail> postDetailClass);
}