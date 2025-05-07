package com.example.hackaton1.model.entity;

import com.example.hackaton1.model.enums.ModelType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ai_requests")
public class AIRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String query;

    @Column(columnDefinition = "TEXT")
    private String response;

    @Enumerated(EnumType.STRING)
    @Column(name = "model_used", nullable = false)
    private ModelType modelUsed;

    @Column(name = "tokens_consumed", nullable = false)
    private int tokensConsumed;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(nullable = false)
    private boolean success;

    // Para solicitudes multimodales
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;
}