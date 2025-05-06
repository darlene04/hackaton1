package com.example.hackaton1.service;

import com.example.hackaton1.dto.request.UsuarioCreateRequest;
import com.example.hackaton1.dto.request.UsuarioUpdateRequest;
import com.example.hackaton1.model.Usuario;
import com.example.hackaton1.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final EmpresaService empresaService;

    @Transactional
    public Usuario createUser(UsuarioCreateRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Usuario usuario = modelMapper.map(request, Usuario.class);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getEmpresaId() != null) {
            usuario.setEmpresa(empresaService.getEmpresaById(request.getEmpresaId()));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario getUserById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional(readOnly = true)
    public List<Usuario> getUsersByCompany(Long empresaId) {
        return usuarioRepository.findByEmpresaId(empresaId);
    }

    @Transactional
    public Usuario updateUser(Long id, UsuarioUpdateRequest request) {
        return usuarioRepository.findById(id)
                .map(user -> {
                    modelMapper.map(request, user);
                    return usuarioRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void deactivateUser(Long id) {
        usuarioRepository.findById(id)
                .ifPresent(user -> {
                    user.setActivo(false);
                    usuarioRepository.save(user);
                });
    }
}