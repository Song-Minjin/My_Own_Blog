package com.innovation.myownblog.model;

import java.time.LocalDateTime;

public interface PostDetail {
    Long getId();
    String getTitle();
    String getAuthor();
    String getContent();
    LocalDateTime getCreatedAt();
}
