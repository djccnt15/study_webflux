package com.example.study_webflux.domain.post.model;

import lombok.Data;

@Data
public class PostCreateRequest {
    
    private Long userId;
    
    private String title;
    
    private String content;
}
