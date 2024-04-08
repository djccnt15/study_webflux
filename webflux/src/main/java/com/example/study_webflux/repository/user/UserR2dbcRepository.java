package com.example.study_webflux.repository.user;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserR2dbcRepository extends ReactiveCrudRepository<UserEntity, Long> {
    
    Flux<UserEntity> findByUserName(String name);
    
    Flux<UserEntity> findByUserNameOrderByIdDesc(String name);
    
    @Modifying
    @Query("DELETE * FROM USER WHERE NAME = :name")
    Mono<Void> deleteByName(String name);
}
