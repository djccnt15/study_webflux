package com.example.study_webflux.repository.post;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostR2dbcRepository extends ReactiveCrudRepository<PostEntity, Long> {
}
