package com.questionproject.questionapp.responses;

import com.questionproject.questionapp.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {
    private Long id;
    private Long postId;
    private Long userId;

    public LikeResponse(Like entity){
        this.id = entity.getId();
        this.postId = entity.getPost().getId();
        this.userId = entity.getUser().getId();
    }
}
