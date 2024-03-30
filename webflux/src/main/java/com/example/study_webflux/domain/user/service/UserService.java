package com.example.study_webflux.domain.user.service;

import com.example.study_webflux.repository.user.UserEntity;
import com.example.study_webflux.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    public Mono<UserEntity> create(String name, String email) {
        var userEntity = UserEntity.builder()
            .userName(name)
            .email(email)
            .build();
        return userRepository.save(userEntity);
    }
    
    public Flux<UserEntity> findAll() {
        return userRepository.findAll();
    }
    
    public Mono<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }
    
    public Mono<Integer> deleteById(Long id) {
        return userRepository.deleteById(id);
    }
    
    public Mono<UserEntity> update(
        Long id,
        String name,
        String email
    ) {
        return userRepository.findById(id)
            .flatMap(user -> {
                user.setUserName(name);
                user.setEmail(email);
                return userRepository.save(user);
            });
    }
}
