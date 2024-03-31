package com.example.study_webflux.domain.post.business;

import com.example.study_webflux.annotation.Business;
import com.example.study_webflux.domain.post.model.PostResponse;
import com.example.study_webflux.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Business
@RequiredArgsConstructor
public class PostBusiness {
    
    private final PostService postService;
    
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
}
