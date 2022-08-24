package com.innovation.myownblog.repository;

import com.innovation.myownblog.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {         // JpaRepository extends : findAll, save 등 메소드 기본적으로 사용 가능해짐
    @EntityGraph(attributePaths = "authorities")                            // @EntityGraph : 쿼리가 수행될 때, Lazy 조회가 아니라 Eager 조회로 authorities 정보를 같이 가져오게 됨
    Optional<User> findOneWithAuthoritiesByUsername(String username);
}