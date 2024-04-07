package com.example.simple_spring.domain.post.service;

import com.example.simple_spring.domain.post.model.PostResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {
    
    public PostResponse createPost(Long id) {
        var post = PostResponse.builder()
            .id(id)
            .userId(id)
            .title("Post Title - [%d]".formatted(id))
            .content("Post content is %d".formatted(id))
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        return post;
    }
}
