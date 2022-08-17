package com.innovation.myownblog.entity.jsonResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateResponse {
    private boolean success;
    private String message;
    private String error;

    public UpdateResponse(Long id){
        this.success = true;
        this.message = "정상적으로" + id + "번 글이 수정되었습니다.";
        this.error = null;
    }
}
