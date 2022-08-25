package com.innovation.myownblog.dto.responseDto;

import com.innovation.myownblog.entity.PostDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetOnePostResponseDto {
    private boolean success;
    private PostDetail data;
    private String error;

    public GetOnePostResponseDto(PostDetail postDetail){
        this.success = true;
        this.data = postDetail;
        this.error = null;
    }

}