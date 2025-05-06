package com.example.hackaton1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioUpdateRequest {
    @NotBlank(message = "Name is required")
    private String nombre;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}
