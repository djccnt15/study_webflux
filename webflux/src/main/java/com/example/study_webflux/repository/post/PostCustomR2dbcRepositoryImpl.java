package com.example.study_webflux.repository.post;

import com.example.study_webflux.repository.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class PostCustomR2dbcRepositoryImpl implements PostCustomR2dbcRepository {
    
    private final DatabaseClient databaseClient;
    
    @Override
    public Flux<PostEntity> findAllByUserId(Long userId) {
        var sql = """
            SELECT
            p.id as pid, p.user_id as userId, p.title, p.content, p.created_at as createdAt, p.updated_at as updatedAt,
            u.id as uid, u.name as name, u.email as email, u.created_at as uCreatedAt, u.updated_at as uUpdatedAt
            FROM post p
            LEFT JOIN user u ON p.user_id = u.id
            WHERE p.user_id = :userId
            """;
        return databaseClient.sql(sql)
            .bind("userId", userId)
            .fetch()
            .all()
            .map(row -> PostEntity.builder()
                .id((Long) row.get("pid"))
                .userId((Long) row.get("userId"))
                .title((String) row.get("title"))
                .content((String) row.get("content"))
                .user(
                    UserEntity.builder()
                        .id((Long) row.get("uid"))
                        .userName((String) row.get("name"))
                        .email((String) row.get("email"))
                        .createdAt((LocalDateTime) row.get("uCreatedAt"))
                        .updatedAt((LocalDateTime) row.get("uUpdatedAt"))
                        .build()
                )
                .createdAt((LocalDateTime) row.get("createdAt"))
                .updatedAt((LocalDateTime) row.get("updatedAt"))
                .build());
    }
}
