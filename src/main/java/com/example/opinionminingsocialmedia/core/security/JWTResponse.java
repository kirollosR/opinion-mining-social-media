package com.example.opinionminingsocialmedia.core.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTResponse {
    private boolean success;
    private String message;
    private String token;
    private String refreshToken;
    private String username;
    private String roles;
}
