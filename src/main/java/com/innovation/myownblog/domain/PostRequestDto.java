package com.innovation.myownblog.domain;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String author;
    private String content;
    private String password;
}
