package com.innovation.myownblog.dto.responseDto;

import com.innovation.myownblog.model.PostDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetOneResponse {
    private boolean success;
    private PostDetail data;
    private String error;

    public GetOneResponse(PostDetail postDetail){
        this.success = true;
        this.data = postDetail;
        this.error = null;
    }

}
