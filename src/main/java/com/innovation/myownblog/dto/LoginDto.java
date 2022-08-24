package com.innovation.myownblog.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50)        // @Valid 관련 어노테이션들 추가
    private String username;        // username,

    @NotNull
    @Size(min = 3, max = 100)
    private String password;        // password 두 개의 필드 보유하고 있는 DTO
}