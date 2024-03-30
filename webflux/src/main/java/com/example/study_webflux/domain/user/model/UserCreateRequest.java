package com.example.study_webflux.domain.user.model;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
@ToString
public class UserCreateRequest {
    
    private String userName;
    
    private String email;
}
