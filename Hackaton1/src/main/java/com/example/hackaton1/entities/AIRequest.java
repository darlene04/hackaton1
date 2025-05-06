package com.example.hackaton1.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AIRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queryText; // Texto de la consulta
    private String responseText; // Respuesta del modelo
    private int tokensConsumed;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private AIModel model; // Modelo utilizado

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
