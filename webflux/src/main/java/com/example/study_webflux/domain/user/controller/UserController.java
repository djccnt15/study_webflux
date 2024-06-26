package com.example.study_webflux.domain.user.controller;

import com.example.study_webflux.domain.post.business.PostBusiness;
import com.example.study_webflux.domain.user.business.UserBusiness;
import com.example.study_webflux.domain.user.model.UserCreateRequest;
import com.example.study_webflux.domain.user.model.UserPostResponse;
import com.example.study_webflux.domain.user.model.UserResponse;
import com.example.study_webflux.domain.user.model.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    
    private final UserBusiness userBusiness;
    
    private final PostBusiness postBusiness;
    
    @PostMapping(path = "")
    public Mono<UserResponse> createUser(
        @RequestBody UserCreateRequest request
    ) {
        var response = userBusiness.createUser(request);
        return response;
    }
    
    @GetMapping(path = "")
    public Flux<UserResponse> findAllUser() {
        var response = userBusiness.findAllUser();
        return response;
    }
    
    @GetMapping(path = "/{id}")
    public Mono<ResponseEntity<UserResponse>> findUser(
        @PathVariable Long id
    ) {
        var response = userBusiness.findUserById(id);
        return response;
    }
    
    @PutMapping(path = "/{id}")
    public Mono<ResponseEntity<UserResponse>> updateUser(
        @PathVariable Long id,
        @RequestBody UserUpdateRequest request
    ) {
        var response = userBusiness.updateUser(id, request);
        return response;
    }
    
    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<?>> deleteUser(
        @PathVariable Long id
    ) {
        var response = userBusiness.deleteUser(id);
        return response;
    }
    
    @DeleteMapping(path = "/search")
    public Mono<ResponseEntity<?>> deleteUserByName(
        @RequestParam String name
    ) {
        return userBusiness.deleteUserByName(name);
    }
    
    @GetMapping(path = "/{id}/post")
    public Flux<UserPostResponse> getUserPost(
        @PathVariable Long id
    ) {
        var postList = postBusiness.findPostByUserId(id);
        return postList;
    }
}
