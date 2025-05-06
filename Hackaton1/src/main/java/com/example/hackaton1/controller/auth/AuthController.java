package com.example.hackaton1.controller.auth;

import com.example.hackaton1.dto.request.AuthLoginRequest;
import com.example.hackaton1.dto.request.AuthRegisterRequest;
import com.example.hackaton1.dto.response.AuthResponse;
import com.example.hackaton1.model.Usuario;
import com.example.hackaton1.security.JwtService;
import com.example.hackaton1.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody AuthRegisterRequest request) {
        Usuario registeredUser = authService.register(request);
        String jwtToken = jwtService.generateToken(registeredUser);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(jwtToken)
                .email(registeredUser.getEmail())
                .role(registeredUser.getRole())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody AuthLoginRequest request) {
        Usuario authenticatedUser = authService.authenticate(request);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(jwtToken)
                .email(authenticatedUser.getEmail())
                .role(authenticatedUser.getRole())
                .build());
    }
}