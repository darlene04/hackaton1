package com.example.hackaton1.entities;
import jakarta.persistence.*;

@Entity
public class CompanyRestriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AIModel model; // Ej: AIModel.OPENAI, AIModel.META, etc.

    private int maxRequestsPerWindow; // Límite de solicitudes
    private int maxTokensPerWindow; // Límite de tokens
    private Duration windowDuration; // Ventana de tiempo (ej: 24 horas)

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
