package com.example.study_webflux.domain.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HealthController {
    
    @GetMapping("/sample/health")
    public Mono<String> getHello() {
        return Mono.just("hello world!");
    }
}
