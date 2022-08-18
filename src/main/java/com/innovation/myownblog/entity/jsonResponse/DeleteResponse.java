package com.innovation.myownblog.entity.jsonResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteResponse {
    private boolean success;
    private String message;
    private String error;

    public DeleteResponse(Long id){
        this.success = true;
        this.message = "정상적으로 " + id + "번 글이 삭제되었습니다.";
        this.error = null;
    }
}
