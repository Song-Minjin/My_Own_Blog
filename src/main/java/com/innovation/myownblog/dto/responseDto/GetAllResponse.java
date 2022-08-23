package com.innovation.myownblog.dto.responseDto;

import com.innovation.myownblog.model.PostSum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetAllResponse {
    private boolean success;
    private List<PostSum> data;
    private String error;

    public GetAllResponse(List<PostSum> postSum){
        this.success = true;
        this.data = postSum;
        this.error = null;
    }
}

