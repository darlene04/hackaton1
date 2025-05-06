package com.example.hackaton1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ruc;
    private LocalDate fechaAfiliacion;
    private boolean activa;

    @ManyToOne
    @JoinColumn(name = "sparky_id")
    private Sparky sparky;

    @OneToOne
    @JoinColumn(name = "administrador_id")
    private Usuario administrador;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Usuario> usuarios = new ArrayList<>();

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<RestriccionEmpresa> restricciones = new ArrayList<>();


}
