package com.example.hackaton1.model.entity;

import com.example.hackaton1.model.enums.ModelType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "user_limits")
public class UserLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "model_type", nullable = false)
    private ModelType modelType;

    @Column(name = "request_limit")
    private Integer requestLimit; // Null significa que hereda de la empresa

    @Column(name = "token_limit")
    private Integer tokenLimit; // Null significa que hereda de la empresa

    @Column(name = "remaining_requests")
    private int remainingRequests;

    @Column(name = "remaining_tokens")
    private int remainingTokens;

    @Column(name = "last_reset")
    private LocalDateTime lastReset;
}