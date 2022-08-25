package com.innovation.myownblog.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePostResponseDto {
    private boolean success;
    private String message;
    private String error;

    public UpdatePostResponseDto(Long id){
        this.success = true;
        this.message = "정상적으로 " + id + "번 글이 수정되었습니다.";
        this.error = null;
    }
}