package com.example.study_webflux.repository.post;

import reactor.core.publisher.Flux;

public interface PostCustomR2dbcRepository {
    
    Flux<PostEntity> findAllByUserId(Long userId);
}
