package com.example.hackaton1.entities;

import jakarta.persistence.*;
import org.apache.catalina.User;
import org.hibernate.mapping.List;

import java.time.LocalDate;
import java.util.ArrayList;

public class SparkyEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ruc; // RUC de la empresa
    private LocalDate affiliationDate; // Fecha de afiliación
    private boolean isActive; // Estado activo/inactivo

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private CompanyAdmin admin; // Administrador principal

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyRestriction> restrictions = new ArrayList<>();
}
