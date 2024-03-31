package com.example.study_webflux.domain.post.model;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
@ToString
public class PostResponse {
    
    private String id;
    
    private String content;
}
