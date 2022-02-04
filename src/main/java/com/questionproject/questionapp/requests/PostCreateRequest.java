package com.questionproject.questionapp.requests;

import lombok.Data;

@Data
public class PostCreateRequest {
    private Long id; //şimdilik
    private String text;
    private String title;
    private Long userId;
}
