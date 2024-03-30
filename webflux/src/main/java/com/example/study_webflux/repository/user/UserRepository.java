package com.example.study_webflux.repository.user;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<UserEntity> save(UserEntity user);
    
    Flux<UserEntity> findAll();
    
    Mono<UserEntity> findById(Long id);
    
    Mono<Integer> deleteById(Long id);
}
