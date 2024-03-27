package com.example.simple_spring.domain.post.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
@ToString
public class PostResponse {
    
    private Long id;
    
    private String content;
}
