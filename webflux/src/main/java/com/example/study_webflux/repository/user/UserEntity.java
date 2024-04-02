package com.example.study_webflux.repository.user;

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
@Table(name = "user")
public class UserEntity extends BaseEntity {
    
    @Column(value = "name")
    private String userName;
    
    private String email;
    
    @Column(value = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    
    @Column(value = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
