package com.innovation.myownblog.entity;

import java.time.LocalDateTime;

public interface PostSum {
    Long getId();
    String getTitle();
    String getAuthor();
    LocalDateTime getCreatedAt();
}
