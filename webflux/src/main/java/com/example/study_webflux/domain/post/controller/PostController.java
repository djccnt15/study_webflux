package com.example.study_webflux.domain.post.controller;

import com.example.study_webflux.domain.post.business.PostBusiness;
import com.example.study_webflux.domain.post.model.PostResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/posts")
public class PostController {
    
    private final PostBusiness postBusiness;
    
    @GetMapping(path = "/{id}")
    public Mono<PostResponse> getPostContents(
        @PathVariable Long id
    ) {
        var contents = postBusiness.getPost(id);
        return contents;
    }
    
    @GetMapping(path = "/list")
    public Flux<PostResponse> getMultiplePostContents(
        @Parameter(in = ParameterIn.QUERY, style = ParameterStyle.MATRIX)
        @RequestParam(name = "id") List<Long> idList
    ) {
        var contents = postBusiness.getMultiplePost(idList);
        return contents;
    }
    
    @GetMapping(path = "/parallel")
    public Flux<PostResponse> getParallelMultiplePostContents(
        @Parameter(in = ParameterIn.QUERY, style = ParameterStyle.MATRIX)
        @RequestParam(name = "id") List<Long> idList
    ) {
        var contents = postBusiness.getParallelMultiplePost(idList);
        return contents;
    }
}
