package com.example.hackaton1.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "user_limits")
public class UserLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AIModel model;

    private int maxRequests; // Límite personalizado para el usuario
    private int maxTokens;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters y Setters
}
