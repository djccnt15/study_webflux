package com.example.study_webflux.domain.user.controller;

import com.example.study_webflux.domain.user.business.UserBusiness;
import com.example.study_webflux.domain.user.model.UserCreateRequest;
import com.example.study_webflux.domain.user.model.UserResponse;
import com.example.study_webflux.domain.user.model.UserUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebFluxTest(UserController.class)
@AutoConfigureWebTestClient
class UserControllerTest {
    
    @Autowired
    private WebTestClient webTestClient;
    
    @MockBean
    private UserBusiness userBusiness;
    
    @Test
    void createUser() {
        var user = UserResponse.builder()
            .id(1L)
            .userName("name")
            .email("email@a.com")
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        
        when(userBusiness.createUser(new UserCreateRequest("name", "email@a.com")))
            .thenReturn(Mono.just(user)
            );
        
        webTestClient.post().uri("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UserCreateRequest("name", "email@a.com"))
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(UserResponse.class)
            .value(res -> {
                assertEquals("name", res.getUserName());
                assertEquals("email@a.com", res.getEmail());
            });
    }
    
    @Test
    void findAllUser() {
        when(userBusiness.findAllUser())
            .thenReturn(
                Flux.just(
                    new UserResponse(1L, "name", "email@a.com", LocalDateTime.now(), LocalDateTime.now()),
                    new UserResponse(2L, "name", "email@a.com", LocalDateTime.now(), LocalDateTime.now()),
                    new UserResponse(3L, "name", "email@a.com", LocalDateTime.now(), LocalDateTime.now())
                )
            );
        
        webTestClient.get().uri("/users")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBodyList(UserResponse.class)
            .hasSize(3);
    }
    
    @Test
    void findUserById() {
        var user = UserResponse.builder()
            .id(1L)
            .userName("name")
            .email("email@a.com")
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        
        when(userBusiness.findUserById(1L))
            .thenReturn(Mono.just(ResponseEntity.ok(user)));
        
        webTestClient.get().uri("/users/1")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(UserResponse.class)
            .value(res -> {
                assertEquals("name", res.getUserName());
                assertEquals("email@a.com", res.getEmail());
            });
    }
    
    @Test
    void userNotFound() {
        when(userBusiness.findUserById(1L))
            .thenReturn(Mono.just(ResponseEntity.notFound().build())
            );
        
        webTestClient.get().uri("/users/1")
            .exchange()
            .expectStatus().is4xxClientError();
    }
    
    @Test
    void updateUser() {
        var user = UserResponse.builder()
            .id(1L)
            .userName("name1")
            .email("email1@a.com")
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        
        when(userBusiness.updateUser(1L, new UserUpdateRequest("name1", "email1@a.com")))
            .thenReturn(Mono.just(ResponseEntity.ok(user))
            );
        
        webTestClient.put().uri("/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UserUpdateRequest("name1", "email1@a.com"))
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(UserResponse.class)
            .value(res -> {
                assertEquals("name1", res.getUserName());
                assertEquals("email1@a.com", res.getEmail());
            });
    }
    
    @Test
    void deleteUser() {
        when(userBusiness.deleteUser(1L))
            .thenReturn(Mono.just(ResponseEntity.noContent().build()));
        
        webTestClient.delete().uri("/users/1")
            .exchange()
            .expectStatus().is2xxSuccessful();
    }
}