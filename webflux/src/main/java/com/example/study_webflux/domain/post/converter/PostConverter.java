package com.example.study_webflux.domain.post.converter;

import com.example.study_webflux.annotation.Converter;
import com.example.study_webflux.domain.post.model.PostCreateRequest;
import com.example.study_webflux.domain.post.model.PostResponse;
import com.example.study_webflux.domain.user.model.UserPostResponse;
import com.example.study_webflux.repository.post.PostEntity;

import java.time.LocalDateTime;

@Converter
public class PostConverter {
    
    public PostEntity toEntity(PostCreateRequest request) {
        var postEntity = PostEntity.builder()
            .userId(request.getUserId())
            .title(request.getTitle())
            .content(request.getTitle())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        return postEntity;
    }
    
    public PostResponse toResponse(PostEntity postEntity) {
        var post = PostResponse.builder()
            .id(postEntity.getId())
            .userId(postEntity.getUserId())
            .title(postEntity.getTitle())
            .content(postEntity.getContent())
            .createdAt(postEntity.getCreatedAt())
            .updatedAt(postEntity.getUpdatedAt())
            .build();
        return post;
    }
    
    public UserPostResponse toUserPostResponse(PostEntity postEntity) {
        var post = UserPostResponse.builder()
            .id(postEntity.getId())
            .userName(postEntity.getUser().getUserName())
            .title(postEntity.getTitle())
            .content(postEntity.getContent())
            .createdAt(postEntity.getCreatedAt())
            .updatedAt(postEntity.getUpdatedAt())
            .build();
        return post;
    }
}
