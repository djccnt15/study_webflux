package com.example.simple_spring.domain.post.controller;

import com.example.simple_spring.domain.post.business.PostBusiness;
import com.example.simple_spring.domain.post.model.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {
    
    private final PostBusiness postBusiness;
    
    @GetMapping(path = "/{id}")
    public PostResponse getPosts(
        @PathVariable Long id
    ) throws Exception {
        var post = postBusiness.getPost(id);
        return post;
    }
}
