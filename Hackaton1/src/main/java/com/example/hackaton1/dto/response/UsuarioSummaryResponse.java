package com.example.hackaton1.dto.response;

import com.example.hackaton1.model.Role;
import lombok.Data;

@Data
public class UsuarioSummaryResponse {
    private Long id;
    private String nombre;
    private String email;
    private Role role;
}
