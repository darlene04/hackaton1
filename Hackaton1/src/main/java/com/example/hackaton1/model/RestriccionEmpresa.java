package com.example.hackaton1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "restricciones_empresa")
@Data
public class RestriccionEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoModelo;
    private int limiteUsoDiario;
    private int limiteUsoMensual;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // Getters y Setters
}
