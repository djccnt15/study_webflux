package com.example.study_webflux.domain.user.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
@ToString
public class UserPostResponse {
    
    private Long id;
    
    private String userName;
    
    private String title;
    
    private String content;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
