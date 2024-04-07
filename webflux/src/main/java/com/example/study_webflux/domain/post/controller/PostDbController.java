package com.example.study_webflux.domain.post.controller;

import com.example.study_webflux.domain.post.business.PostBusiness;
import com.example.study_webflux.domain.post.model.PostCreateRequest;
import com.example.study_webflux.domain.post.model.PostResponse;
import com.example.study_webflux.domain.post.model.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/posts/db")
public class PostDbController {
    
    private final PostBusiness postBusiness;
    
    @PostMapping
    public Mono<PostResponse> createPost(PostCreateRequest request) {
        var post = postBusiness.createDbPost(request);
        return post;
    }
    
    @GetMapping
    public Flux<PostResponse> findAllPost() {
        var postList = postBusiness.findAllDbPost();
        return postList;
    }
    
    @GetMapping(path = "/{id}")
    public Mono<ResponseEntity<PostResponse>> findPost(
        @PathVariable Long id
    ) {
        var post = postBusiness.findDbPost(id);
        return post;
    }
    
    @PutMapping(path = "/{id}")
    public Mono<ResponseEntity<PostResponse>> updatePost(
        @PathVariable Long id,
        @RequestBody PostUpdateRequest request
    ) {
        var post = postBusiness.updateDbPost(id, request);
        return post;
    }
    
    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<?>> deletePost(Long id) {
        var post = postBusiness.deletePost(id);
        return post;
    }
}
