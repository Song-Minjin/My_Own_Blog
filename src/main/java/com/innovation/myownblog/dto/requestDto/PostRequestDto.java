package com.innovation.myownblog.dto.requestDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {
    private String title;
    private String author;
    private String content;
    private String password;
    private LocalDateTime createdAt;
}