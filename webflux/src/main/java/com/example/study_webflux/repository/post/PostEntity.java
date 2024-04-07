package com.example.study_webflux.repository.post;

import com.example.study_webflux.repository.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class PostEntity extends BaseEntity {
    
    @Column(value = "user_id")
    private Long userId;
    
    private String title;
    
    private String content;
    
    @Column(value = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    
    @Column(value = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
