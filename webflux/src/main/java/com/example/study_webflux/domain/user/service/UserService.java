package com.example.study_webflux.domain.user.service;

import com.example.study_webflux.repository.user.UserEntity;
import com.example.study_webflux.repository.user.UserR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UserService {
    
    // private final UserRepository userRepository;
    private final UserR2dbcRepository userR2dbcRepository;
    private final ReactiveRedisTemplate<String, UserEntity> reactiveRedisTemplate;
    
    private String getUserCacheKey(Long id) {
        return "user:%d".formatted(id);
    }
    
    public Mono<UserEntity> create(UserEntity userEntity) {
        return userR2dbcRepository.save(userEntity);
    }
    
    public Flux<UserEntity> findAll() {
        var userList = userR2dbcRepository.findAll();
        return userList;
    }
    
    public Mono<UserEntity> findById(Long id) {
        var redisKey = getUserCacheKey(id);
        var user = reactiveRedisTemplate.opsForValue()
            .get(redisKey)
            .switchIfEmpty(
                userR2dbcRepository.findById(id)
                    .flatMap(
                        userEntity -> reactiveRedisTemplate.opsForValue()
                            .set(redisKey, userEntity, Duration.ofSeconds(30))
                            .then(Mono.just(userEntity))
                    )
            );
        return user;
    }
    
    public Mono<UserEntity> update(
        Long id,
        String name,
        String email
    ) {
        var user = userR2dbcRepository.findById(id)
            .flatMap(userEntity -> {
                userEntity.setUserName(name);
                userEntity.setEmail(email);
                return userR2dbcRepository.save(userEntity);
            })
            .flatMap(userEntity -> reactiveRedisTemplate
                .unlink(getUserCacheKey(id))
                .then(Mono.just(userEntity))
            );
        return user;
    }
    
    public Mono<Void> deleteById(Long id) {
        return userR2dbcRepository.deleteById(id)
            .then(reactiveRedisTemplate.unlink(getUserCacheKey(id)))
            .then(Mono.empty());
    }
    
    public Mono<Void> deleteByName(String name) {
        return userR2dbcRepository.deleteByName(name);
    }
}
