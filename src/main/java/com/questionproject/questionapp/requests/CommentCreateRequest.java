package com.questionproject.questionapp.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
        private Long id;
        private Long postId;
        private Long userId;
        private String text;
}
