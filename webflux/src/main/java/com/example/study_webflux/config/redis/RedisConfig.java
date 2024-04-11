package com.example.study_webflux.config.redis;

import com.example.study_webflux.repository.user.UserEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RedisConfig implements ApplicationListener<ApplicationReadyEvent> {
    
    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        reactiveRedisTemplate.opsForValue().get("1")
            .doOnSuccess(i -> log.info("initialize redis connection"))
            .doOnError(e -> log.error("fail to initialize redis connection: {}", e.getMessage()))
            .subscribe();
    }
    
    // 커스텀 레디스 템플릿은 config를 통해 주입해야 함
    @Bean
    public ReactiveRedisTemplate<String, UserEntity> reactiveRedisUserTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        var objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        
        Jackson2JsonRedisSerializer<UserEntity> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, UserEntity.class);
        
        RedisSerializationContext<String, UserEntity> serializationContext = RedisSerializationContext
            .<String, UserEntity>newSerializationContext()
            .key(RedisSerializer.string())
            .hashKey(RedisSerializer.string())
            .value(jsonRedisSerializer)
            .hashValue(jsonRedisSerializer)
            .build();
        
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }
}
