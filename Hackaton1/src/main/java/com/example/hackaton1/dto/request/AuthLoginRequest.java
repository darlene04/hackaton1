package com.example.hackaton1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthLoginRequest {
    @NotBlank @Email
    private String email;

    @NotBlank @Size(min = 8)
    private String password;
}