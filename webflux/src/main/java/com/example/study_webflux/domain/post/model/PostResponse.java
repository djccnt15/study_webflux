package com.example.study_webflux.domain.post.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
@ToString
public class PostResponse {
    
    private Long id;
    
    private Long userId;
    
    private String title;
    
    private String content;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    public PostResponse(
        Long id,
        String content
    ) {
        this.id = id;
        this.userId = -1L;
        this.title = "error";
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
