package com.example.hackaton1.repository;

import com.example.hackaton1.model.Role;
import com.example.hackaton1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository<T extends User> extends JpaRepository<T, Integer> {
    Optional<T> findByEmail(String correo);
    List<User> findAllByRole(Role role);
}