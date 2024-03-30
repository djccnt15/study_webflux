package com.example.study_webflux.repository.user;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryImpl implements UserRepository {
    
    private final ConcurrentHashMap<Long, UserEntity> userHashMap = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);
    
    @Override
    public Mono<UserEntity> save(UserEntity user) {
        var now = LocalDateTime.now();
        if (user.getId() == null) {
            user.setId(sequence.getAndAdd(1));
            user.setCreatedAt(now);
        }
        user.setUpdatedAt(now);
        userHashMap.put(user.getId(), user);
        return Mono.just(user);
    }
    
    @Override
    public Flux<UserEntity> findAll() {
        return Flux.fromIterable(userHashMap.values());
    }
    
    @Override
    public Mono<UserEntity> findById(Long id) {
        return Mono.justOrEmpty(userHashMap.getOrDefault(id, null));
    }
    
    @Override
    public Mono<Integer> deleteById(Long id) {
        UserEntity user = userHashMap.getOrDefault(id, null);
        if (user == null) {
            return Mono.just(0);
        }
        userHashMap.remove(id, user);
        return Mono.just(1);
    }
}
