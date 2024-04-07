package com.example.study_webflux.domain.post.service;

import com.example.study_webflux.domain.post.model.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    
    private final WebClient webClient;
    private final String url = "http://127.0.0.1:8090";
    
    public Mono<PostResponse> getPostContent(Long id) {
        String uriString = UriComponentsBuilder.fromHttpUrl(url)
            .path("/posts/%d".formatted(id))
            .buildAndExpand()
            .toUriString();
        
        var content = webClient.get()
            .uri(uriString)
            .retrieve()
            .bodyToMono(PostResponse.class)
            .onErrorResume(
                error -> Mono.just(
                    new PostResponse(id, "Fallback data %d".formatted(id))
                )
            );
        return content;
    }
    
    public Flux<PostResponse> getMultiplePostContents(List<Long> idList) {
        return Flux.fromIterable(idList)
            .flatMap(this::getPostContent);
    }
    
    public Flux<PostResponse> getParallelMultiplePostContents(List<Long> idList) {
        return Flux.fromIterable(idList)
            .parallel()
            .runOn(Schedulers.parallel())
            .flatMap(this::getPostContent)
            .sequential();
    }
}
