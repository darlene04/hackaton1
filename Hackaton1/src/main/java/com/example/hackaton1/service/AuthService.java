package com.example.hackaton1.service;

import com.example.hackaton1.dto.request.AuthLoginRequest;
import com.example.hackaton1.dto.request.AuthRegisterRequest;
import com.example.hackaton1.exception.AuthenticationException;
import com.example.hackaton1.model.Role;
import com.example.hackaton1.model.Usuario;
import com.example.hackaton1.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final EmpresaService empresaService;

    public Usuario register(AuthRegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new AuthenticationException("Email already registered");
        }

        Usuario usuario = modelMapper.map(request, Usuario.class);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        // Set default role if not specified
        if (usuario.getRole() == null) {
            usuario.setRole(Role.ROLE_USER);
        }

        // Associate with company if empresaId provided
        if (request.getEmpresaId() != null) {
            usuario.setEmpresa(empresaService.getEmpresaById(request.getEmpresaId()));
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario authenticate(AuthLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthenticationException("User not found"));
    }
}
