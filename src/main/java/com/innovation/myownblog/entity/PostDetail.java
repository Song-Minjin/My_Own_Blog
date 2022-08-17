package com.innovation.myownblog.entity;

import java.time.LocalDateTime;

public interface PostDetail {
    Long getId();
    String getTitle();
    String getAuthor();
    String getContent();
    LocalDateTime getCreatedAt();
}
