package com.example.simple_spring.domain.post.business;

import com.example.simple_spring.annotation.Business;
import com.example.simple_spring.domain.post.model.PostResponse;
import com.example.simple_spring.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@Business
@RequiredArgsConstructor
public class PostBusiness {
    
    private final PostService postService;
    
    public PostResponse getPost(Long id) throws Exception {
        var rng = new Random();
        int sleepTime = rng.nextInt(10) * 10;
        Thread.sleep(sleepTime);
        
        if (id > 10L) {
            throw new Exception("too long");
        }
        
        var post = postService.createPost(id);
        return post;
    }
}
