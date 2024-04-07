package com.example.study_webflux.domain.post.business;

import com.example.study_webflux.annotation.Business;
import com.example.study_webflux.domain.post.converter.PostConverter;
import com.example.study_webflux.domain.post.model.PostCreateRequest;
import com.example.study_webflux.domain.post.model.PostResponse;
import com.example.study_webflux.domain.post.model.PostUpdateRequest;
import com.example.study_webflux.domain.post.service.PostDbService;
import com.example.study_webflux.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Business
@RequiredArgsConstructor
public class PostBusiness {
    
    private final PostService postService;
    private final PostDbService postDbService;
    private final PostConverter postConverter;
    
    public Mono<PostResponse> getPost(Long id) {
        var contents = postService.getPostContent(id);
        return contents;
    }
    
    public Flux<PostResponse> getMultiplePost(List<Long> idList) {
        var contents = postService.getMultiplePostContents(idList);
        return contents;
    }
    
    public Flux<PostResponse> getParallelMultiplePost(List<Long> idList) {
        var contents = postService.getParallelMultiplePostContents(idList);
        return contents;
    }
    
    public Mono<PostResponse> createDbPost(PostCreateRequest request) {
        var postEntity = postConverter.toEntity(request);
        var post = postDbService.create(postEntity)
            .map(postConverter::toResponse);
        return post;
    }
    
    public Flux<PostResponse> findAllDbPost() {
        var postList = postDbService.findAll()
            .map(postConverter::toResponse);
        return postList;
    }
    
    public Mono<ResponseEntity<PostResponse>> findDbPost(Long id) {
        var post = postDbService.findById(id)
            .map(p -> ResponseEntity.ok().body(postConverter.toResponse(p)))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
        return post;
    }
    
    public Mono<ResponseEntity<PostResponse>> updateDbPost(
        Long id,
        PostUpdateRequest request
    ) {
        var post = postDbService.update(id, request.getTitle(), request.getContent())
            .map(postEntity -> ResponseEntity.ok(postConverter.toResponse(postEntity)))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
        
        return post;
    }
    
    public Mono<ResponseEntity<?>> deletePost(Long id) {
        return postDbService.deleteById(id)
            .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
