package com.example.hackaton1.entities;

import jakarta.persistence.*;
import org.hibernate.mapping.List;

import java.time.LocalDate;
import java.util.ArrayList;
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ruc;
    private LocalDate affiliationDate;
    private boolean isActive;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private Administrador admin; // Administrador principal

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyRestriction> restrictions = new ArrayList<>();
}
