package com.example.study_webflux.handler.webflux;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class WebfluxHandler {
    public Mono<ServerResponse> getString(ServerRequest request) {
        return ServerResponse.ok().bodyValue("hello functional endpoints");
    }
}
