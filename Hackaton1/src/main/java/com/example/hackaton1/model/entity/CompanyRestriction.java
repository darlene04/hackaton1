package com.example.hackaton1.model.entity;

import com.example.hackaton1.model.enums.ModelType;
import com.example.hackaton1.model.enums.TimeWindow;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "company_restrictions")
public class CompanyRestriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(name = "model_type", nullable = false)
    private ModelType modelType;

    @Column(name = "request_limit", nullable = false)
    private int requestLimit;

    @Column(name = "token_limit", nullable = false)
    private int tokenLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_window", nullable = false)
    private TimeWindow timeWindow;

    // Getters y setters
}

