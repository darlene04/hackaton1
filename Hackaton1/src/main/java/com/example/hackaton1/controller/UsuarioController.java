package com.example.hackaton1.controller;

import com.example.hackaton1.dto.request.UsuarioCreateRequest;
import com.example.hackaton1.dto.request.UsuarioUpdateRequest;
import com.example.hackaton1.dto.response.UsuarioResponse;
import com.example.hackaton1.dto.response.UsuarioSummaryResponse;
import com.example.hackaton1.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ModelMapper modelMapper;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN') or hasRole('ROLE_COMPANY_ADMIN')")
    public ResponseEntity<UsuarioResponse> createUser(
            @Valid @RequestBody UsuarioCreateRequest request) {
        return ResponseEntity.ok(
                modelMapper.map(
                        usuarioService.createUser(request),
                        UsuarioResponse.class
                )
        );
    }

    @GetMapping("/empresa/{empresaId}")
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN') or (hasRole('ROLE_COMPANY_ADMIN') and @securityService.isCompanyAdmin(#empresaId, principal))")
    public ResponseEntity<List<UsuarioSummaryResponse>> getUsersByCompany(
            @PathVariable Long empresaId) {
        return ResponseEntity.ok(
                usuarioService.getUsersByCompany(empresaId).stream()
                        .map(user -> modelMapper.map(user, UsuarioSummaryResponse.class))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN') or (hasRole('ROLE_COMPANY_ADMIN') and @securityService.isSameCompany(#id, principal))")
    public ResponseEntity<UsuarioResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(
                modelMapper.map(
                        usuarioService.getUserById(id),
                        UsuarioResponse.class
                )
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN') or (hasRole('ROLE_COMPANY_ADMIN') and @securityService.isSameCompany(#id, principal))")
    public ResponseEntity<UsuarioResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioUpdateRequest request) {
        return ResponseEntity.ok(
                modelMapper.map(
                        usuarioService.updateUser(id, request),
                        UsuarioResponse.class
                )
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SPARKY_ADMIN') or (hasRole('ROLE_COMPANY_ADMIN') and @securityService.isSameCompany(#id, principal))")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
        usuarioService.deactivateUser(id);
        return ResponseEntity.noContent().build();
    }
}
