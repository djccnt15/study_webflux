package com.example.study_webflux.domain.user.service;

import com.example.study_webflux.repository.user.UserEntity;
import com.example.study_webflux.repository.user.UserR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    
    // private final UserRepository userRepository;
    private final UserR2dbcRepository userR2dbcRepository;
    
    public Mono<UserEntity> create(UserEntity userEntity) {
        return userR2dbcRepository.save(userEntity);
    }
    
    public Flux<UserEntity> findAll() {
        var userList = userR2dbcRepository.findAll();
        return userList;
    }
    
    public Mono<UserEntity> findById(Long id) {
        var user = userR2dbcRepository.findById(id);
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
            });
        
        return user;
    }
    
    public Mono<Void> deleteById(Long id) {
        return userR2dbcRepository.deleteById(id);
    }
    
    public Mono<Void> deleteByName(String name) {
        return userR2dbcRepository.deleteByName(name);
    }
}
