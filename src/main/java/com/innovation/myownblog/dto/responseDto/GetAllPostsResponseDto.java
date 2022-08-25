package com.innovation.myownblog.dto.responseDto;

import com.innovation.myownblog.entity.PostSummary;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetAllPostsResponseDto {
    private boolean success;
    private List<PostSummary> data;
    private String error;

    public GetAllPostsResponseDto(List<PostSummary> postSum){
        this.success = true;
        this.data = postSum;
        this.error = null;
    }
}
