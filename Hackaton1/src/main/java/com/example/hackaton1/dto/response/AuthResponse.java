package com.example.hackaton1.dto.response;

import com.example.hackaton1.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
    private String email;
    private Role role;
}