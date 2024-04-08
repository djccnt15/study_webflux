package com.example.study_webflux.repository.post;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PostR2dbcRepository extends ReactiveCrudRepository<PostEntity, Long>, PostCustomR2dbcRepository {
    
    Flux<PostEntity> findByUserId(Long id);
}
