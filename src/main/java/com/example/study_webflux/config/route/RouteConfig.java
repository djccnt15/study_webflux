package com.example.study_webflux.config.route;

import com.example.study_webflux.handler.webflux.WebfluxHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {
    
    private final WebfluxHandler handler;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route()
            .GET("/hello-functional", handler::getString)
            .build();
    }
}
