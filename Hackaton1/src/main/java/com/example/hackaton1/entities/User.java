package com.example.hackaton1.entities;

import jakarta.persistence.*;
import org.hibernate.mapping.List;

import javax.management.relation.Role;
import java.util.ArrayList;

@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password; // Encriptada

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER; // Rol predefinido

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company; // Empresa a la que pertenece

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLimit> limits = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AIRequest> requests = new ArrayList<>();
}
