package com.example.study_webflux.domain.user.converter;

import com.example.study_webflux.annotation.Converter;
import com.example.study_webflux.domain.user.model.UserCreateRequest;
import com.example.study_webflux.domain.user.model.UserResponse;
import com.example.study_webflux.repository.user.UserEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class UserConverter {
    
    public UserEntity toEntity(UserCreateRequest request) {
        var userEntity = UserEntity.builder()
            .userName(request.getUserName())
            .email(request.getEmail())
            .build();
        
        return userEntity;
    }
    
    public UserResponse toResponse(UserEntity userEntity) {
        var user = UserResponse.builder()
            .id(userEntity.getId())
            .userName(userEntity.getUserName())
            .email(userEntity.getEmail())
            .createdAt(userEntity.getCreatedAt())
            .updatedAt(userEntity.getUpdatedAt())
            .build();
        
        return user;
    }
}
