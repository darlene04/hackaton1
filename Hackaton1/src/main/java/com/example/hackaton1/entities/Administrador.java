package com.example.hackaton1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import javax.management.relation.Role;

@Entity

public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password; // Encriptada con Spring Security

    @Enumerated(EnumType.STRING)
    private Role role = Role.Administrador; // Rol predefinido

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company; // Relación bidireccional

    // Getters y Setters
}

