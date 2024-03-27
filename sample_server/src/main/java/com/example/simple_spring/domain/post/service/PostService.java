package com.example.simple_spring.domain.post.service;

import com.example.simple_spring.domain.post.model.PostResponse;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    public PostResponse createPost(Long id) {
        var post = PostResponse.builder()
            .id(id)
            .content("Post content is %d".formatted(id))
            .build();
        return post;
    }
}
