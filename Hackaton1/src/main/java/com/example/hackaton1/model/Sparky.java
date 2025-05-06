package com.example.hackaton1.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Sparky {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "sparky", cascade = CascadeType.ALL)
    private List<Empresa> empresas = new ArrayList<>();

    // Getters y Setters
}
