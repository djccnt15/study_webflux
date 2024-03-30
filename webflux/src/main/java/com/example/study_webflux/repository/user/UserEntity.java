package com.example.study_webflux.repository.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class UserEntity {
    
    private Long id;
    
    private String userName;
    
    private String email;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
