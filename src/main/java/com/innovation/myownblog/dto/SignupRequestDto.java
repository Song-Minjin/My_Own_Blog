package com.innovation.myownblog.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innovation.myownblog.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank(message =  "아이디를 입력해주세요.")
    @Pattern(regexp="^[a-zA-Z0-9]{4,12}$", message="아이디를 4~12자로 입력해주세요.(알파벳 대소문자 및 숫자)")
    /*@Size(min = 4, max = 12)*/
    private String username;

    @JsonIgnore
    @NotBlank(message =  "비밀번호를 입력해주세요.")
    @Pattern(regexp="^[a-zA-Z0-9]{4,32}$", message="비밀번호를 4~32자로 입력해주세요.(알파벳 대소문자 및 숫자)")
    private String password;

    @JsonIgnore
    @NotBlank(message =  "비밀번호를 한 번 더 입력해주세요.")
    @Pattern(regexp="^[a-zA-Z0-9]{4,32}$", message="비밀번호를 4~32자로 입력해주세요.(알파벳 대소문자 및 숫자)")
    private String password_check;

    @NotBlank(message =  "닉네임을 입력해주세요.")
    @Size(min = 3, max = 50)
    private String nickname;

    private Set<AuthorityDto> authorityDtoSet;

    public static SignupRequestDto from(User user) {
        if(user == null) return null;
        return SignupRequestDto.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}