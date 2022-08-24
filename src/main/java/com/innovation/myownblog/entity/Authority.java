package com.innovation.myownblog.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity     // 데이터베이스 테이블과 1:1로 매칭되는 객체
@Table(name = "authority")      // 테이블명 설정
@Getter             // get
@Setter             // set
@Builder                    // 빌더
@AllArgsConstructor         // 생성자      -> lombok은 실무에서는 고려를 해서 덜 자유롭게 써야 할 경우들이 있음
@NoArgsConstructor
public class Authority {

    @Id
    @Column(name = "authority_name", length = 50)       // 권한명이라는 PK
    private String authorityName;
}