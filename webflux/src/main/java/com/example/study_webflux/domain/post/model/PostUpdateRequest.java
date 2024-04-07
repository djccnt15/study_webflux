package com.example.study_webflux.domain.post.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
public class PostUpdateRequest {
    
    private String title;
    
    private String content;
}
