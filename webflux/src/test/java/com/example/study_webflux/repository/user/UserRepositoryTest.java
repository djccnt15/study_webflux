package com.example.study_webflux.repository.user;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {
    
    private final UserRepository userRepository = new UserRepositoryImpl();
    
    @Test
    void save() {
        var user = UserEntity.builder()
            .userName("name")
            .email("email@test.com")
            .build();
        
        StepVerifier.create(userRepository.save(user))
            .assertNext(u -> {
                assertEquals(1L, u.getId());
                assertEquals("name", u.getUserName());
            })
            .verifyComplete();
    }
    
    @Test
    void findAll() {
        var user = UserEntity.builder()
            .userName("name")
            .email("email@test.com")
            .build();
        userRepository.save(user);
        
        StepVerifier.create(userRepository.findAll())
            .expectNextCount(1)
            .verifyComplete();
    }
    
    @Test
    void findById() {
        var user = UserEntity.builder()
            .userName("name")
            .email("email@test.com")
            .build();
        userRepository.save(user);
        
        StepVerifier.create(userRepository.findById(1L))
            .assertNext(u -> {
                assertEquals(1L, u.getId());
                assertEquals("name", u.getUserName());
            })
            .verifyComplete();
    }
    
    @Test
    void deleteById() {
        var user = UserEntity.builder()
            .userName("name")
            .email("email@test.com")
            .build();
        userRepository.save(user);
        
        StepVerifier.create(userRepository.deleteById(1L))
            .expectNext(1)
            .verifyComplete();
    }
}