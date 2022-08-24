package com.innovation.myownblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innovation.myownblog.dto.requestDto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자 생성
@Setter
@Getter
@Entity // 테이블과 연계됨
@EntityListeners(AuditingEntityListener.class)
public class Post extends Timestamped { // 생성,수정 시간을 자동 생성

    // ID 자동 생성 및 증가
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private String content;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long userId;

    public Post(String title, String username, String contents, String password) {
        this.userId = userId;
        this.title = title;
        this.userNickname = userNickname;
        this.content = contents;
        this.password = password;
    }

    public Post(PostRequestDto requestDto, long userId, String userNickname) {
        this.userId = this.userId;
        this.title = requestDto.getTitle();
        this.userNickname = this.userNickname;
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto) {
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.userNickname = userNickname;
        this.content = requestDto.getContent();
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}