package com.example.study_webflux.domain.user.business;

import com.example.study_webflux.annotation.Business;
import com.example.study_webflux.domain.user.converter.UserConverter;
import com.example.study_webflux.domain.user.model.UserCreateRequest;
import com.example.study_webflux.domain.user.model.UserResponse;
import com.example.study_webflux.domain.user.model.UserUpdateRequest;
import com.example.study_webflux.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Business
@RequiredArgsConstructor
public class UserBusiness {
    
    private final UserService userService;
    private final UserConverter userConverter;
    
    public Mono<UserResponse> createUser(UserCreateRequest request) {
        var userEntity = userConverter.toEntity(request);
        var userCreate = userService.create(userEntity);
        return userCreate.map(userConverter::toResponse);
    }
    
    public Flux<UserResponse> findAllUser() {
        var userList = userService.findAll();
        return userList.map(userConverter::toResponse);
    }
    
    public Mono<ResponseEntity<UserResponse>> findUserById(Long id) {
        return userService.findById(id)
            .map(user -> ResponseEntity.ok(userConverter.toResponse(user)))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
    
    public Mono<ResponseEntity<UserResponse>> updateUser(
        Long id,
        UserUpdateRequest request
    ) {
        return userService.update(id, request.getUserName(), request.getEmail())
            .map(user -> ResponseEntity.ok(userConverter.toResponse(user)))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
    
    public Mono<ResponseEntity<?>> deleteUser(Long id) {
        return userService.deleteById(id)
            .then(Mono.just(ResponseEntity.noContent().build()));
    }
    
    public Mono<ResponseEntity<?>> deleteUserByName(String name) {
        return userService.deleteByName(name)
            .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
