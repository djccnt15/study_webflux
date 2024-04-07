package com.example.study_webflux.domain.post.service;

import com.example.study_webflux.repository.post.PostEntity;
import com.example.study_webflux.repository.post.PostR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostDbService {
    
    private final PostR2dbcRepository postR2dbcRepository;
    
    public Mono<PostEntity> create(PostEntity post) {
        return postR2dbcRepository.save(post);
    }
    
    public Flux<PostEntity> findAll() {
        var postList = postR2dbcRepository.findAll();
        return postList;
    }
    
    public Mono<PostEntity> findById(Long id) {
        var post = postR2dbcRepository.findById(id);
        return post;
    }
    
    public Mono<PostEntity> update(
        Long id,
        String title,
        String content
    ) {
        var post = postR2dbcRepository.findById(id)
            .flatMap(postEntity -> {
                postEntity.setTitle(title);
                postEntity.setContent(content);
                return postR2dbcRepository.save(postEntity);
            });
        
        return post;
    }
    
    public Mono<Void> deleteById(Long id) {
        return postR2dbcRepository.deleteById(id);
    }
}
